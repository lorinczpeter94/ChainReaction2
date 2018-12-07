package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.example.lorinczpeter94.chainreaction2.R

class BackgroundSelector(
    private var context: Context) {

    fun chooseColor(noOfCircles: Int, currentPlayer: Int): Drawable {
        //chooses background color,  depends on no. of circles and current players
        when (currentPlayer) {
            1 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.red_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.red_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.red_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.red_circle1)!!
            }
            2 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.green_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.green_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            3 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.blue_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.blue_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.blue_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            4 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.yellow_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.yellow_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.yellow_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            5 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.purple_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.purple_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.purple_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            6 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.cyan_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.cyan_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.cyan_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            7 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.orange_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.orange_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.orange_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            8 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(context, R.drawable.white_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(context, R.drawable.white_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(context, R.drawable.white_circle3)!!
                }
                else -> ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
            }
            else -> return ContextCompat.getDrawable(context, R.drawable.green_circle1)!!
        }
    }
}