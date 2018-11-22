package com.example.lorinczpeter94.chainreaction2.game_activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.welcome_activity.PLAYERNUM

class SmallGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_game)
        val playerNumber = intent.getIntExtra(PLAYERNUM, 2)
        Toast.makeText(this, "Player number selected: $playerNumber", Toast.LENGTH_LONG).show()
    }
}