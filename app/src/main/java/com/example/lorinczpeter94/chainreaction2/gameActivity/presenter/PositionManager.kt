package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter


class PositionManager(private var height: Int,
                      private var width: Int){

    fun isInCorner(id: Int): Boolean {
        val indexes = idToInt(id)
        val row = indexes[0]
        val column = indexes[1]
        //returns true if for the corner indexes
        return (row == 0 && column == 0 || row == 0 && column == width-1 ||
                row == height-1 && column == 0 || row == height-1 && column == width-1)
    }

    fun isOnSide(id: Int): Boolean {
        val indexes = idToInt(id)
        val row = indexes[0]
        val column = indexes[1]
        //returns true for the indexes on the side of the matrix but not in the corner
        return (row in 1 until height-1 && column == 0 || row in 1 until height-1 && column == width-1 ||
                column in 1 until width-1 && row == 0 || column in 1 until width-1 && row == height-1)
    }

    fun hisCircle(color: Int, currentPlayer: Int): Boolean {
        //returns true if on the indexes is the current player's color
        return color == currentPlayer
    }

    fun idToInt(id: Int): ArrayList<Int> {
        val indexArray = ArrayList<Int>()
        val i = (id % 10) - 1
        val j = ((id / 10) % 10) - 1
        indexArray.add(j)
        indexArray.add(i)
        return indexArray
    }

}