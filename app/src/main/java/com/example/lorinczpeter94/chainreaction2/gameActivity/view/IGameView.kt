package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.widget.ImageView

interface IGameView{
    fun setOneCircle(imageView: ImageView, oneCircle: Drawable)
    fun midAnimation(imageView: CustomImageView, color: Drawable)
    fun dispatchTouchEvent(ev: MotionEvent?): Boolean
}