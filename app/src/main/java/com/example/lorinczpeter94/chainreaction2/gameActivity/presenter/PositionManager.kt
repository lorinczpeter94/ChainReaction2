package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.model.AssociatedMatrix


class PositionManager(private var height: Int,
                      private var width: Int){


    fun isInCorner(row: Int, column: Int): Boolean {
        //returns true if for the corner indexes
        return (row == 0 && column == 0 || row == 0 && column == width-1 ||
                row == height-1 && column == 0 || row == height-1 && column == width-1)
    }

    fun isOnSide(row: Int, column: Int): Boolean {
        //returns true for the indexes on the side of the matrix but not in the corner
        return (row in 1 until height-1 && column == 0 || row in 1 until height-1 && column == width-1 ||
                column in 1 until width-1 && row == 0 || column in 1 until width-1 && row == height-1)
    }



}