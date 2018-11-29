package com.example.lorinczpeter94.chainreaction2.gameActivity.model

class ActivePlayer(
    private var currentPlayer:Int,
    private var playerNumber:Int,
    private var players:Array<Int>) : IActivePlayer{


    override fun getCurrentPlayer():Int{
        return currentPlayer
    }

    override fun setPlayerNumber(playerNumber: Int){
        this.playerNumber = playerNumber
    }

    override fun nextPlayer(){
        currentPlayer = currentPlayer % playerNumber + 1
    }


}