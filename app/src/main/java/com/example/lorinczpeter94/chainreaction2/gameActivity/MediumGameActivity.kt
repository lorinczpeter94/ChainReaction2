package com.example.lorinczpeter94.chainreaction2.gameActivity

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.GameObject
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.GamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.IGamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM





class MediumGameActivity : AppCompatActivity(), IGameView {


    internal lateinit var iGamePresenter:IGamePresenter
    //var associatedMatrix: Array<Array<Int>> = Array(8) {Array(6){0} }
    var associatedMatrix =Array(8) {Array(6) { GameObject() } }
    var associatedViewMatrix =Array(8) {Array(6) { GameObject() } }
    var activePlayer = ActivePlayer(1, 2)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_game)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val playerCircle = findViewById<ImageView>(R.id.playerCircle)
        playerCircle.background = ContextCompat.getDrawable(this, R.drawable.red_circle1)
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)
        activePlayer.setPlayerNumber(playerNumber)



        //animation for later.
//        val circle:ImageView = findViewById(R.id.imgCircle)
//
//        val anim = RotateAnimation(0f, 360f, -5f, -5f)
//        anim.interpolator = LinearInterpolator()
//        anim.repeatCount = Animation.INFINITE
//        anim.duration = 700
//
//        circle.startAnimation(ganim)

    }


    fun onGameObjectClicked(view: View){
        // Triggered when a cell is pushed in the game
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)


        iGamePresenter = GamePresenter(this, this, associatedMatrix, playerNumber, activePlayer)
        val objectClicked = view as ImageView
        iGamePresenter.elementClicked(objectClicked)
    }


    override fun setOnecircle(imageView: ImageView, oneCircle:Drawable) {
        // Sets the red_circle1 drawable as background
        imageView.background = oneCircle

    }

    override fun setTwoCircles(imageView: ImageView, twoCircles:Drawable) {
        // Sets the red_circle2 drawable as background
        imageView.background = twoCircles

    }

    override fun setThreeCircles(imageView: ImageView, threeCircles:Drawable) {
        // Sets the red_circle3 drawable as background
        imageView.background = threeCircles

    }
    override fun setNoCircle(imageView: ImageView) {


        imageView.background = ContextCompat.getDrawable(this, R.drawable.no_circle)
    }



}
