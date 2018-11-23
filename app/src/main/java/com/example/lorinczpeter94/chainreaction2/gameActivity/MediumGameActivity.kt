package com.example.lorinczpeter94.chainreaction2.gameActivity

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.GamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.IGamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM



class MediumGameActivity : AppCompatActivity(), IGameView {

    internal lateinit var iGamePresenter:IGamePresenter
    var associatedMatrix:Array<Array<Int>> = Array(8) {Array(6){0} }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_game)




        //animation for later.
//        val circle:ImageView = findViewById(R.id.imgCircle)
//
//        val anim = RotateAnimation(0f, 360f, -5f, -5f)
//        anim.interpolator = LinearInterpolator()
//        anim.repeatCount = Animation.INFINITE
//        anim.duration = 700
//
//        circle.startAnimation(anim)

    }


    fun onGameObjectClicked(view: View){
        // Triggered when a cell is pushed in the game
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)
        iGamePresenter = GamePresenter(this, associatedMatrix, playerNumber)
        val objectClicked = view as ImageView
        iGamePresenter.elementClicked(objectClicked)
    }


    override fun setOnecircle(imageView: ImageView) {
        // Sets the circle1 drawable as background
        imageView.background = ContextCompat.getDrawable(this, R.drawable.circle1)
    }

    override fun setTwoCircles(imageView: ImageView) {
        // Sets the circle2 drawable as background
        imageView.background = ContextCompat.getDrawable(this, R.drawable.circle2)

    }

    override fun setThreeCircles(imageView: ImageView) {
        // Sets the circle3 drawable as background
        imageView.background = ContextCompat.getDrawable(this, R.drawable.circle3)

    }



}
