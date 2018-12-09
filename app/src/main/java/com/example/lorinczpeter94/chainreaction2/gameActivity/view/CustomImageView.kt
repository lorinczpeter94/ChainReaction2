package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.CustomViewPresenter
import kotlin.properties.Delegates

@SuppressLint("ViewConstructor")

class CustomImageView(
    context: Context,
    private var activePlayer: ActivePlayer
) : ImageView(context), ICustomImageView {

    private var color: Int = 0
    private var numberOfCircles: Int = 0
    private var simulation: Boolean = false
    private var circleComeSignal: Int by Delegates.observable(0) { _, _, newValue ->
        explodeCircleCameIn(newValue)
        checkActive()
    }

    var viewPresenter: CustomViewPresenter? = null


    init {
        background = ContextCompat.getDrawable(context, R.drawable.no_circle)
        viewPresenter = CustomViewPresenter(this, context)
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
        val anim = RotateAnimation(0f, 360f, (width / 2).toFloat(), (height/2).toFloat())
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