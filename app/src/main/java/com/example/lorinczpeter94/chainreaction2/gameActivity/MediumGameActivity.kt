package com.example.lorinczpeter94.chainreaction2.gameActivity

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.GamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.IGamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.GameLayout
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM


class MediumGameActivity : AppCompatActivity(), IGameView {

    private var iGamePresenter: IGamePresenter? = null
    private var activePlayer = ActivePlayer(1, 2, (Array(9) { true }))
    private var viewMatrix: Array<Array<CustomImageView>>? = null
    private var optionsMenu: Menu? = null



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.game_menu, menu)
        optionsMenu = menu
        viewMatrix.let {
            iGamePresenter = GamePresenter(this as IGameView, this as Activity, this as Context, optionsMenu!!, activePlayer, it!!)
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_game)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)



        val playerCircle = findViewById<ImageView>(R.id.playerCircle)
        playerCircle.background = ContextCompat.getDrawable(this, R.drawable.red_circle1)
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)

        activePlayer.setPlayerNumber(playerNumber)

        createLayout()

    }

    private fun createLayout() {
        val relativeLayout: RelativeLayout = findViewById(R.id.relativeLayout)
        viewMatrix = Array(GamePresenter.noOfRows) {
            Array(GamePresenter.noOfColumns) {
                CustomImageView(this as Context, activePlayer) }
        }


        viewMatrix?.let {
            val gameLayout = GameLayout(this, relativeLayout, it)
            gameLayout.createLayout()
        }
    }

    override fun setOneCircle(imageView: ImageView, oneCircle: Drawable) {
        imageView.background = oneCircle
    }

    override fun midAnimation(imageView: CustomImageView, color: Drawable) {
        val myLayout: RelativeLayout = findViewById(R.id.relativeLayout)
        val animationView1 = ImageView(this)
        val animationView2 = ImageView(this)
        val animationView3 = ImageView(this)
        val animationView4 = ImageView(this)

        myLayout.addView(animationView1)
        myLayout.addView(animationView2)
        myLayout.addView(animationView3)
        myLayout.addView(animationView4)

        setOneCircle(animationView1, color)
        setOneCircle(animationView2, color)
        setOneCircle(animationView3, color)
        setOneCircle(animationView4, color)

        val animRight = TranslateAnimation(imageView.x, imageView.x + imageView.width, imageView.y, imageView.y)
        val animLeft = TranslateAnimation(imageView.x, imageView.x - imageView.width, imageView.y, imageView.y)
        val animUp = TranslateAnimation(imageView.x, imageView.x, imageView.y, imageView.y + imageView.height)
        val animDown = TranslateAnimation(imageView.x, imageView.x, imageView.y, imageView.y - imageView.height)

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

    override fun setOnecircleTop(imageView: ImageView, color: Drawable) {
        imageView.background = color
    }

}