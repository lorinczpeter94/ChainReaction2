package com.example.lorinczpeter94.chainreaction2.welcome_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.game_activity.GameActivity
import com.example.lorinczpeter94.chainreaction2.welcome_activity.view.IWelcomeView

class WelcomeActivity : AppCompatActivity(), IWelcomeView {


    //var playerNumberSpn: Spinner = findViewById(R.id.spn_mapSize)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        mapSizeSpinnerInitializer()
        playerNumberSpinnerInitializer()

        val btn_play: Button = findViewById(R.id.btn_play)
        btn_play.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun mapSizeSpinnerInitializer() {
        var mapSizeSpn: Spinner = findViewById(R.id.spn_mapSize)
        ArrayAdapter.createFromResource(
            this,
            R.array.map_size,
            R.layout.my_spinner_text
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.my_spinner_text)
            mapSizeSpn.adapter = adapter
        }

    }

    override fun playerNumberSpinnerInitializer() {
        var playerNumberSpn: Spinner = findViewById(R.id.spn_playerNumber)
        ArrayAdapter.createFromResource(
            this,
            R.array.player_number,
            R.layout.my_spinner_text
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.my_spinner_text)
            playerNumberSpn.adapter = adapter
        }
    }





}
