package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.model.AssociatedMatrix

class PlayerManager(
    private var playerNumber: Int,
    private var associatedMatrix: AssociatedMatrix
) {

    private var players:Array<Int> = Array(playerNumber+2) {0}


    fun calcPlayerFields(){
        for(i in 0.. associatedMatrix.getHeight()){
            for (j in 0..associatedMatrix.getWidth()){
                if (associatedMatrix.getCircles(i, j) > 0){
                    players[associatedMatrix.getColor(i, j)]++
                }
            }
        }
    }

    fun checkWinner(): Boolean{
        players = Array(playerNumber+2){0}

        calcPlayerFields()
        var db = 0
        for(i in 1 until players.size){
            if(players[i] != 0)
                db++
        }

        return db == 1

    }


    fun checkPlayer(currentPlayer: Int): Boolean{
        players = Array(playerNumber+2){0}
        calcPlayerFields()

        return (players[currentPlayer] != 0)
    }

}