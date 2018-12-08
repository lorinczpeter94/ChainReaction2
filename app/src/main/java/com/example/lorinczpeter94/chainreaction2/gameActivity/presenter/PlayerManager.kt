package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView

class PlayerManager(
    private var playerNumber: Int,
    private var viewMatrix: Array<Array<CustomImageView>>
) {

    private var players: Array<Int> = Array(playerNumber + 1) { 0 }

    private fun calcPlayerFields() {
        for (i in 0 until GamePresenter.noOfRows) {
            for (j in 0 until GamePresenter.noOfColumns) {
                if (viewMatrix[i][j].getNumberOfCircles() > 0) {
                    players[viewMatrix[i][j].getColor()]++
                }
            }
        }
    }

    fun getGameState(): Array<Int>{
        players = Array(playerNumber + 1) { 0 }
        calcPlayerFields()
        return players
    }

    fun checkWinner(): Boolean {
        players = Array(playerNumber + 1) { 0 }

        calcPlayerFields()

        var count = 0
        for (i in 1 until players.size) {
            if (players[i] != 0)
                count++
        }

        return count == 1
    }
}