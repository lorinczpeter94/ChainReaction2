package com.example.lorinczpeter94.chainreaction2.gameActivity.model

class ActivePlayer(
    private var currentPlayer:Int,
    private var playerNumber:Int){


    fun getCurrentPlayer():Int{
        return currentPlayer
    }

    fun setPlayerNumber(playerNumber: Int){
        this.playerNumber = playerNumber
    }

    fun nextPlayer(){
        currentPlayer = currentPlayer % playerNumber + 1
    }

}