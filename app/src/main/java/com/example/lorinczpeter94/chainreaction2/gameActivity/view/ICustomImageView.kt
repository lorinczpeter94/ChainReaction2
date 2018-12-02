package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.graphics.drawable.Drawable
import android.widget.ImageView

interface ICustomImageView{
    fun setColor(color: Int)
    fun setOnecircle(oneCircle: Drawable)
    fun setTwoCircles(twoCircles: Drawable)
    fun setThreeCircles(threeCircles: Drawable)
    fun setNoCircle()
    fun setActiveGameObject()
    fun stopActiveGameObject()
    fun midAnimation(color: Drawable)
    fun incNumberOfCircles()
    fun setOnecircleTop(imageView: ImageView, color: Drawable)
}