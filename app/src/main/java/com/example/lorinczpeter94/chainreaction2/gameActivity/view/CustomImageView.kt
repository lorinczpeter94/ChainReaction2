package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.CustomViewPresenter
import kotlin.properties.Delegates

class CustomImageView(
    context: Context,
    activity: Activity,
    private var activePlayer: ActivePlayer
) : ImageView(context), ICustomImageView {
    var viewPresenter: CustomViewPresenter? = null
    private var color: Int = 0

    private var numberOfCircles: Int = 0

    private var simulation: Boolean = false

    private var circleComeSignal: Int by Delegates.observable(0) { property, oldValue, newValue ->
        explodeCircleCameIn(newValue)
        checkActive()
    }


    init {
        background = ContextCompat.getDrawable(context, R.drawable.no_circle)
        viewPresenter = CustomViewPresenter(this, context, activity)
        this.setOnClickListener {
            viewPresenter?.elementClicked(numberOfCircles, color, id, activePlayer)
        }

    }


    override fun setColor(color: Int) {
        this.color = color
    }


    override fun incNumberOfCircles() {
        numberOfCircles++
    }

    override fun zeroNumberOfCircles() {
        numberOfCircles = 0
    }

    override fun setSimulation(simulation: Boolean) {
        this.simulation = simulation
    }

    fun circleComeIn(setColor: Int) {
        circleComeSignal = setColor

    }

    override fun setNumberOfCircles(numberOfCircles: Int) {
        this.numberOfCircles = numberOfCircles
    }

    override fun explodeCircleCameIn(setcolor: Int) {
        viewPresenter?.circleCameIn(setcolor, id, simulation)
    }

    override fun getNumberOfCircles(): Int {
        return numberOfCircles
    }

    override fun getColor(): Int {
        return color
    }

    override fun setOnecircle(oneCircle: Drawable) {
        // Sets the red_circle1 drawable as background
        background = oneCircle

    }

    override fun setNoCircle() {
        //Sets an empty background for the imageView

        background = ContextCompat.getDrawable(context, R.drawable.no_circle)
    }

    override fun setOnecircleTop(imageView: ImageView, color: Drawable) {
        imageView.background = color
    }

    private fun checkActive() {
        viewPresenter?.checkForActive(id)
    }


    override fun setActiveGameObject() {
        //"Activates" the game objects by rotating them
        val anim = RotateAnimation(0f, 360f, 75f, 75f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 1000
        startAnimation(anim)
    }

    override fun stopActiveGameObject() {
        //Stops the rotation of the imageView
        clearAnimation()
    }


}