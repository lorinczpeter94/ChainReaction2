package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.content.Context
import android.media.MediaPlayer
import com.example.lorinczpeter94.chainreaction2.R

class SoundManager(
    context: Context
) {

    private val mediaPlayer = MediaPlayer.create(context, R.raw.explode)

    fun startSound(){
        mediaPlayer.start()
    }
}