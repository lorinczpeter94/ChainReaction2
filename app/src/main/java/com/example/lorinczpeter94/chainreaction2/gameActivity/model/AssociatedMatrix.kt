package com.example.lorinczpeter94.chainreaction2.gameActivity.model

open class AssociatedMatrix(
    private var row: Int,
    private var column: Int):IAssociatedMatrix {

    private var associatedMatrix: Array<Array<GameObject>> = Array(row) {Array(column) { GameObject() } }


    override fun getColor(i:Int, j:Int):Int{
        return associatedMatrix[i][j].color
    }

    override fun getCircles(i:Int, j:Int):Int{
        return associatedMatrix[i][j].circles
    }

    override fun setColor(i:Int, j:Int, color: Int){
        associatedMatrix[i][j].color = color
    }

    override fun incCircles(i:Int, j:Int){
        associatedMatrix[i][j].circles++
    }

    override fun setZeroCircles(i:Int, j:Int){
        associatedMatrix[i][j].circles = 0
    }

    override fun getHeight():Int{
        return row-1
    }
    override fun getWidth():Int{
        return column-1
    }

    override fun hisCircle(row: Int, column: Int, currentPlayer: Int): Boolean {
        //returns true if on the indexes is the current player's color
        return associatedMatrix[row][column].color == currentPlayer
    }


}