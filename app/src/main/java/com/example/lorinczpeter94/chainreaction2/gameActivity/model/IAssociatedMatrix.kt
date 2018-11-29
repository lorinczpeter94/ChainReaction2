package com.example.lorinczpeter94.chainreaction2.gameActivity.model

interface IAssociatedMatrix {
    fun getColor(i:Int, j:Int):Int
    fun getCircles(i:Int, j:Int):Int
    fun setColor(i:Int, j:Int, color: Int)
    fun incCircles(i:Int, j:Int)
    fun setZeroCircles(i:Int, j:Int)
    fun getHeight():Int
    fun getWidth():Int
    fun hisCircle(row: Int, column: Int, currentPlayer: Int): Boolean
}