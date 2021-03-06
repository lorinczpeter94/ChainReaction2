package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.graphics.drawable.Drawable
import android.widget.ImageView

interface ICustomImageView{
    fun setColor(color: Int)
    fun setOnecircle(oneCircle: Drawable)
    fun setNoCircle()
    fun setActiveGameObject()
    fun stopActiveGameObject()
    fun incNumberOfCircles()
    fun zeroNumberOfCircles()
    fun setOnecircleTop(imageView: ImageView, color: Drawable)
    fun getNumberOfCircles(): Int
    fun getColor(): Int
    fun setNumberOfCircles(numberOfCircles: Int)
    fun explodeCircleCameIn(setcolor: Int)
    fun setSimulation(simulation: Boolean)
}