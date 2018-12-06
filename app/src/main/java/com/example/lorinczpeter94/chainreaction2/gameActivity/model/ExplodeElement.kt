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

class ExplodeElement(
    context: Context,
    activity: Activity,
    private var activePlayer: ActivePlayer
){
    var viewPresenter: CustomViewPresenter? = null
    private var color: Int = 0

    private var numberOfCircles: Int = 0

    private var circleComeSignal: Int by Delegates.observable(0) { property, oldValue, newValue ->
        explodeCircleCameIn(newValue)
    }


    init {
        viewPresenter = CustomViewPresenter(this, context, activity)
        this.setOnClickListener {
            viewPresenter?.elementClicked(numberOfCircles, color, id, activePlayer)
        }

    }


    fun setColor(color: Int) {
        this.color = color
    }


    fun incNumberOfCircles() {
        numberOfCircles++
    }

    override fun zeroNumberOfCircles() {
        numberOfCircles = 0
    }

    fun circleComeIn(setColor: Int) {
        //TODO: CIRCLE came in need to increase number of circles
        circleComeSignal = setColor


    }

    override fun setNumberOfCircles(numberOfCircles: Int) {
        this.numberOfCircles = numberOfCircles
    }

    override fun explodeCircleCameIn(setcolor: Int) {
        viewPresenter?.circleCameIn(setcolor, id)
    }

    override fun getNumberOfCircles(): Int {
        return numberOfCircles
    }

    override fun getColor(): Int {
        return color
    }


}