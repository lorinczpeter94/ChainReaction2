package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.graphics.drawable.Drawable
import android.widget.ImageView

interface IGameView{
    fun setOnecircle(imageView: ImageView, oneCircle:Drawable)
    fun setTwoCircles(imageView: ImageView, twoCircles:Drawable)
    fun setThreeCircles(imageView: ImageView, threeCircles:Drawable)
    fun setNoCircle(imageView: ImageView)
    fun setActiveGameObject(imageView: ImageView)
    fun stopActiveGameObject(imageView: ImageView)
}