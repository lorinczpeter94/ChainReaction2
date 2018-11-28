package com.example.lorinczpeter94.chainreaction2.gameActivity

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TableLayout
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.GameObject
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.GamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.IGamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM





class MediumGameActivity : AppCompatActivity(), IGameView {


    private lateinit var iGamePresenter:IGamePresenter

    private var associatedMatrix =Array(8) {Array(6) { GameObject() } }

    private var activePlayer = ActivePlayer(1, 2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_game)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val playerCircle = findViewById<ImageView>(R.id.playerCircle)
        playerCircle.background = ContextCompat.getDrawable(this, R.drawable.red_circle1)
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)
        activePlayer.setPlayerNumber(playerNumber)

    }


    fun onGameObjectClicked(view: View){
        // Triggered when a cell is pushed in the game

        iGamePresenter = GamePresenter(this, this, associatedMatrix, activePlayer)
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
        //Sets an empty background for the imageView

        imageView.background = ContextCompat.getDrawable(this, R.drawable.no_circle)
    }

    override fun setActiveGameObject(imageView: ImageView) {
        //"Activates" the game objects by rotating them
        val anim = RotateAnimation(0f, 360f, 75f, 75f)
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 1000
        imageView.startAnimation(anim)
    }

    override fun stopActiveGameObject(imageView: ImageView) {
        //Stops the rotation of the imageView
        imageView.clearAnimation()
    }

    override fun midAnimation(imageView: ImageView, color: Drawable) {
        val myLayout: RelativeLayout = findViewById(R.id.relativeLayout)
        val animationView1 = ImageView(this)
        val animationView2 = ImageView(this)
        val animationView3 = ImageView(this)
        val animationView4 = ImageView(this)

        myLayout.addView(animationView1)
        myLayout.addView(animationView2)
        myLayout.addView(animationView3)
        myLayout.addView(animationView4)

        setOnecircle(animationView1, color)
        setOnecircle(animationView2, color)
        setOnecircle(animationView3, color)
        setOnecircle(animationView4, color)

        val animRight = TranslateAnimation(imageView.x, imageView.x + 100f, imageView.y, imageView.y)
        val animLeft = TranslateAnimation(imageView.x, imageView.x - 100f, imageView.y, imageView.y)
        val animUp = TranslateAnimation(imageView.x, imageView.x, imageView.y, imageView.y +  100f)
        val animDown = TranslateAnimation(imageView.x, imageView.x, imageView.y, imageView.y - 100f)

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
