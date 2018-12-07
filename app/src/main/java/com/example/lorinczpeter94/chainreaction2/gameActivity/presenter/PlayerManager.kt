package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView


class PlayerManager(
    private var playerNumber: Int,
    private var viewMatrix: Array<Array<CustomImageView>>
) {

    private var players: Array<Int> = Array(playerNumber + 1) { 0 }

    fun calcPlayerFields() {
        for (i in 0 until GamePresenter.noOfRows) {
            for (j in 0 until GamePresenter.noOfColumns) {
                if (viewMatrix[i][j].getNumberOfCircles() > 0) {
                    players[viewMatrix[i][j].getColor()]++
                }
            }
        }


        //-----------------------
//        println("Matix:")
//        for (i in 0 until GamePresenter.noOfRows) {
//            for (j in 0 until GamePresenter.noOfColumns) {
//                print("(${viewMatrix[i][j].getColor()}, ${viewMatrix[i][j].getNumberOfCircles()}) ")
//            }
//            println()
//        }
//
//
//        println("players array:")
//        println()
//        for (i in players) {
//            print("$i ")
//        }
//        println()
        //-----------------------
    }


    fun checkWinner(): Boolean {
        players = Array(playerNumber + 1) { 0 }

        calcPlayerFields()
        var db = 0
        for (i in 1 until players.size) {
            if (players[i] != 0)
                db++
        }

        for (i in 1 until players.size) {
            if (players[i] == 0) {

            }
        }

        return db == 1

    }

    fun checkGameState(): Array<Int>{
        players = Array(playerNumber + 1) { 0 }
        calcPlayerFields()
        return  players

    }

    fun checkPlayer(currentPlayer: Int): Boolean {
        //players = Array(playerNumber + 1) { 0 }
        //calcPlayerFields()

//        println("players array:")
//        println()
//        for (i in players) {
//            print("$i ")
//        }
//        println()
        return (players[currentPlayer] == 0)
    }

}