package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.content.Context
import android.media.MediaPlayer
import com.example.lorinczpeter94.chainreaction2.R

class SoundManager(
    context: Context
) {

    private val explodeSound = MediaPlayer.create(context, R.raw.explode)
    private val putSound = MediaPlayer.create(context, R.raw.put)

    fun startExplodeSound(){
        explodeSound.start()
    }

    fun startPutSound(){
        putSound.start()
    }
}