package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView

class ExplodeSimulator(
    private var activePlayer: ActivePlayer,
    private var viewMatrix: Array<Array<CustomImageView>>) {

    init {

    }

    fun setColor(color: Int) {
        this.color = color
    }


    fun incNumberOfCircles() {
        numberOfCircles++
    }

}