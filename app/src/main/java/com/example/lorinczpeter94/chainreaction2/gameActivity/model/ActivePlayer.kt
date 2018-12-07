package com.example.lorinczpeter94.chainreaction2.gameActivity.model

class ActivePlayer(
    private var currentPlayer: Int,
    private var playerNumber: Int,
    private var players: Array<Boolean>
) : IActivePlayer {

    init {
       // println("PLAYERNUMBER: $playerNumber")
    }
    private var roundCounter: Int = 0

    override fun setActivePlayer(player: Int){
        currentPlayer = player
    }

    fun getPlayerNumber(): Int {
        return playerNumber
    }

    override fun getCurrentPlayer(): Int {
        return currentPlayer
    }

    override fun setPlayerNumber(playerNumber: Int) {
        this.playerNumber = playerNumber
        println("setPlayernumber: $playerNumber")
    }

    override fun nextPlayer() {
        currentPlayer = currentPlayer % playerNumber + 1
        if (!players[currentPlayer]){
            nextPlayer()
        }
        roundCounter++
    }

    fun setPlayerFalse(index: Int){
        players[index] = false
    }

    fun getRoundCounter(): Int{
        return roundCounter
    }

    fun getWinner(): Int{
        for (i in 1 until players.size){
            if (players[i]){
                return i
            }
        }
        return 0
    }

}