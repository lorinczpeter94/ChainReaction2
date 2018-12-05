package com.example.lorinczpeter94.chainreaction2.gameActivity.model

interface IActivePlayer{
    fun getCurrentPlayer():Int
    fun setPlayerNumber(playerNumber: Int)
    fun nextPlayer()

}