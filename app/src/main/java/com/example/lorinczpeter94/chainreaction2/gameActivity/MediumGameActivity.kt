package com.example.lorinczpeter94.chainreaction2.gameActivity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.BackgroundSelector
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.GamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.presenter.IGamePresenter
import com.example.lorinczpeter94.chainreaction2.gameActivity.utilities.IDtoInt
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM


class MediumGameActivity : AppCompatActivity(), IGameView {





    private var iGamePresenter:IGamePresenter? = null
    private var activePlayer = ActivePlayer(1, 2, (Array(9){0}))




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium_game)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        val relativeLayout: RelativeLayout = findViewById(R.id.relativeLayout)


            val playerCircle = findViewById<ImageView>(R.id.playerCircle)
            playerCircle.background = ContextCompat.getDrawable(this, R.drawable.red_circle1)
            val playerNumber = intent.getIntExtra(PLAYERNUM, 2)


            activePlayer.setPlayerNumber(playerNumber)

            iGamePresenter = GamePresenter(this as IGameView, this as Activity, this as Context,  activePlayer)


    }














    fun onGameObjectClicked(view: View){
        // Triggered when a cell is pushed in the game


        val objectClicked = view as ImageView


        iGamePresenter?.elementClicked(objectClicked)

    }

}