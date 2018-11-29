package com.example.lorinczpeter94.chainreaction2.gameActivity.model

class Players(
    private var players:Array<Int>){



    fun add(poz:Int){
        players[poz]++
    }

    fun succ(poz:Int){
        if (players[poz] > 0) {
            players[poz]--
        }
    }

    fun getWinner():Boolean{
        var db = 0
        for(i in 1 until players.size){
            if(players[i] != 0)
                db++
        }
        return db == 1
    }
}