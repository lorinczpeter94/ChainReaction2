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
    private var activePlayer: ActivePlayer): ImageView(context), ICustomImageView {
    var viewPresenter: CustomViewPresenter? = null
    private var color: Int = 0

    private var numberOfCircles: Int = 0

    private var circleComeSignal: Int by Delegates.observable(0) {
            property, oldValue, newValue ->
        circleComeIn(newValue)
    }

    init {
        color = 0
        background = ContextCompat.getDrawable(context, R.drawable.no_circle)
        viewPresenter = CustomViewPresenter(this, context, activity)
        this.setOnClickListener {
            viewPresenter!!.elementClicked(numberOfCircles, color, id, activePlayer)
        }

    }








    override fun setColor(color: Int){
        this.color = color
    }


    override fun incNumberOfCircles() {
        numberOfCircles++
    }

    override fun zeroNumberOfCircles() {
        numberOfCircles = 0
    }

    fun circleComeIn(color: Int){
        //TODO: CIRCLE came in need to increase number of circles

    }

    override fun setOnecircle(oneCircle:Drawable) {
        // Sets the red_circle1 drawable as background
        background = oneCircle

    }

    override fun setTwoCircles(twoCircles:Drawable) {
        // Sets the red_circle2 drawable as background
        background = twoCircles

    }

    override fun setThreeCircles(threeCircles:Drawable) {
        // Sets the red_circle3 drawable as background
        background = threeCircles

    }
    override fun setNoCircle() {
        //Sets an empty background for the imageView

        background = ContextCompat.getDrawable(context, R.drawable.no_circle)
    }

    override fun setOnecircleTop(imageView: ImageView, color: Drawable) {
        imageView.background = color
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

    override fun midAnimation(color: Drawable) {
        val myLayout: RelativeLayout = findViewById(R.id.relativeLayout)
        val animationView1 = ImageView(context)
        val animationView2 = ImageView(context)
        val animationView3 = ImageView(context)
        val animationView4 = ImageView(context)

        myLayout.addView(animationView1)
        myLayout.addView(animationView2)
        myLayout.addView(animationView3)
        myLayout.addView(animationView4)

        //setOnecircle(animationView1, color)
        //setOnecircle(animationView2, color)
        //setOnecircle(animationView3, color)
        //setOnecircle(animationView4, color)

        val animRight = TranslateAnimation(this.x, this.x + 100f, this.y, this.y)
        val animLeft = TranslateAnimation(this.x, this.x - 100f, this.y, this.y)
        val animUp = TranslateAnimation(this.x, this.x, this.y, this.y +  100f)
        val animDown = TranslateAnimation(this.x, this.x, this.y, this.y - 100f)

        animRight.interpolator = LinearInterpolator()
        animLeft.interpolator = LinearInterpolator()
        animUp.interpolator = LinearInterpolator()
        animDown.interpolator = LinearInterpolator()

        animRight.duration = 200
        animLeft.duration = 200
        animUp.duration = 200
        animDown.duration = 200

        animationView1.startAnimation(animRight)
        animationView2.startAnimation(animLeft)
        animationView3.startAnimation(animUp)
        animationView4.startAnimation(animDown)

        myLayout.removeView(animationView1)
        myLayout.removeView(animationView2)
        myLayout.removeView(animationView3)
        myLayout.removeView(animationView4)

    }
}