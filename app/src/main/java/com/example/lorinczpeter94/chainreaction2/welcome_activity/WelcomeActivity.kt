package com.example.lorinczpeter94.chainreaction2.welcome_activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.LargeGameActivity
import com.example.lorinczpeter94.chainreaction2.gameActivity.MediumGameActivity
import com.example.lorinczpeter94.chainreaction2.gameActivity.SmallGameActivity
import com.example.lorinczpeter94.chainreaction2.welcome_activity.presenter.IWelcomePresenter
import com.example.lorinczpeter94.chainreaction2.welcome_activity.presenter.WelcomePresenter
import com.example.lorinczpeter94.chainreaction2.welcome_activity.view.IWelcomeView

val PLAYERNUM:String ="PLAYER_NUM"

class WelcomeActivity : AppCompatActivity(), IWelcomeView {

    internal lateinit var iWelcomePresenter:IWelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)




        mapSizeSpinnerInitializer()
        playerNumberSpinnerInitializer()

        iWelcomePresenter = WelcomePresenter(this)
        val btnPlay: Button = findViewById(R.id.btn_play)
        btnPlay.setOnClickListener {

            val spnMapSize:Spinner = findViewById(R.id.spn_mapSize)
            val spnPlayerNumber:Spinner = findViewById(R.id.spn_playerNumber)
            iWelcomePresenter.settingsSpinner(spnMapSize.selectedItem.toString(),
                spnPlayerNumber.selectedItem.toString())

        }


    }

    override fun mapSizeSpinnerInitializer() {
        //Adds the elements to spnMapsize spinner from R.values.strings
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
        //Adds the elements to spnPlayerNumber spinner from R.values.strings
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


    override fun openSmallMapActivity(playerNumber:Int) {
        val intent = Intent(this, SmallGameActivity::class.java).apply {
            putExtra(PLAYERNUM, playerNumber)
        }
        startActivity(intent)
    }

    override fun openMediumMapActivity(playerNumber:Int) {
        val intent = Intent(this, MediumGameActivity::class.java).apply {
            putExtra(PLAYERNUM, playerNumber)
        }
        startActivity(intent)
    }

    override fun openLargeMapActivity(playerNumber:Int) {
        val intent = Intent(this, LargeGameActivity::class.java).apply {
            putExtra(PLAYERNUM, playerNumber)
        }
        startActivity(intent)
    }




}
