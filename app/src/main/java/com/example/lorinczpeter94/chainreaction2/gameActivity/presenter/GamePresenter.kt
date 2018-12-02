package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.GameLayout
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView


class GamePresenter(
    private var iIGameView: IGameView,
    private var activity: Activity,
    private var context: Context,
    private var activePlayer: ActivePlayer

) : IGamePresenter {
    companion object {
        val noOfRows: Int = 8
        val noOfColumns: Int = 6
    }
    private var viewMatrix: Array<Array<CustomImageView>>? = null


    init {
        val relativeLayout: RelativeLayout = activity.findViewById(R.id.relativeLayout)


        viewMatrix = Array(noOfRows) { Array(noOfColumns) { CustomImageView(context, activity, activePlayer) } }


        viewMatrix?.let {
            var gameLayout = GameLayout(context, relativeLayout, it)
            gameLayout.createLayout()
        }
    }

    override fun elementClicked(imageView: ImageView) {

    }




























    private fun playerPut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView): Boolean {
        return true
    }

    private fun chooseColor(noOfCircles: Int, currentPlayer: Int): Drawable {
        //chooses background color,  depends on no. of circles and current players
        when (currentPlayer) {
            1 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.red_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.red_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.red_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.red_circle1)!!
            }
            2 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            3 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.blue_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.blue_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.blue_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            4 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.yellow_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.yellow_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.yellow_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            5 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.purple_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.purple_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.purple_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            6 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.cyan_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.cyan_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.cyan_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            7 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.orange_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.orange_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.orange_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            8 -> return when (noOfCircles) {
                1 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.white_circle1)!!
                }
                2 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.white_circle2)!!
                }
                3 -> {
                    ContextCompat.getDrawable(iIGameView as Context, R.drawable.white_circle3)!!
                }
                else -> ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
            }
            else -> return ContextCompat.getDrawable(iIGameView as Context, R.drawable.green_circle1)!!
        }
    }

    private fun explode(row: Int, column: Int, currentPlayer: Int, imageView: ImageView) {
    }

    //  private fun getNeighbours(row: Int, column: Int): ArrayList<List<Int>> {
    //returns the indexes of the neighbours of circle on indexes [row][column]

//    if (positionManager.isInCorner(row, column)) {
//        if (row == 0 && column == 0) {
//            //top left corner
//            val neighbours = ArrayList<List<Int>>()
//            val currentNeighbour1 = ArrayList<Int>()
//            val currentNeighbour2 = ArrayList<Int>()
//
//            currentNeighbour1.add(0)
//            currentNeighbour1.add(1)
//            neighbours.add(currentNeighbour1)
//
//            currentNeighbour2.add(1)
//            currentNeighbour2.add(0)
//            neighbours.add(currentNeighbour2)
//
//            return neighbours
//
//        } else if (row == 0 && column == 5) {
//            //top right corner
//            val neighbours = ArrayList<List<Int>>()
//            val currentNeighbour1 = ArrayList<Int>()
//            val currentNeighbour2 = ArrayList<Int>()
//
//            currentNeighbour1.add(1)
//            currentNeighbour1.add(5)
//            neighbours.add(currentNeighbour1)
//
//            currentNeighbour2.add(0)
//            currentNeighbour2.add(4)
//            neighbours.add(currentNeighbour2)
//
//            return neighbours
//
//        } else if (row == 7 && column == 0) {
//            //bottom left corner
//            val neighbours = ArrayList<List<Int>>()
//            val currentNeighbour1 = ArrayList<Int>()
//            val currentNeighbour2 = ArrayList<Int>()
//
//            currentNeighbour1.add(6)
//            currentNeighbour1.add(0)
//            neighbours.add(currentNeighbour1)
//
//            currentNeighbour2.add(7)
//            currentNeighbour2.add(1)
//            neighbours.add(currentNeighbour2)
//
//            return neighbours
//
//        } else { //row == 7 && column == 5
//            //bottom right corner
//            val neighbours = ArrayList<List<Int>>()
//            val currentNeighbour1 = ArrayList<Int>()
//            val currentNeighbour2 = ArrayList<Int>()
//
//            currentNeighbour1.add(7)
//            currentNeighbour1.add(4)
//            neighbours.add(currentNeighbour1)
//
//            currentNeighbour2.add(6)
//            currentNeighbour2.add(5)
//            neighbours.add(currentNeighbour2)
//
//            return neighbours
//        }
//
//    } else if (positionManager.isOnSide(row, column)) {
//        when {
//            column == 0 -> {    //left side
//                val neighbours = ArrayList<List<Int>>()
//                val currentNeighbour1 = ArrayList<Int>()
//                val currentNeighbour2 = ArrayList<Int>()
//                val currentNeighbour3 = ArrayList<Int>()
//
//                currentNeighbour1.add(row - 1)
//                currentNeighbour1.add(column)
//                neighbours.add(currentNeighbour1)   //the top one
//
//                currentNeighbour2.add(row)
//                currentNeighbour2.add(column + 1)
//                neighbours.add(currentNeighbour2)   //the right one
//
//                currentNeighbour3.add(row + 1)
//                currentNeighbour3.add(column)
//                neighbours.add(currentNeighbour3)   //the bottom one
//
//                return neighbours
//
//            }
//            column == 5 -> { //right side
//                val neighbours = ArrayList<List<Int>>()
//                val currentNeighbour1 = ArrayList<Int>()
//                val currentNeighbour2 = ArrayList<Int>()
//                val currentNeighbour3 = ArrayList<Int>()
//
//                currentNeighbour1.add(row - 1)
//                currentNeighbour1.add(column)
//                neighbours.add(currentNeighbour1)
//
//                currentNeighbour2.add(row + 1)
//                currentNeighbour2.add(column)
//                neighbours.add(currentNeighbour2)
//
//                currentNeighbour3.add(row)
//                currentNeighbour3.add(column - 1)
//                neighbours.add(currentNeighbour3)
//
//                return neighbours
//
//            }
//            row == 0 -> {    //top side
//                val neighbours = ArrayList<List<Int>>()
//                val currentNeighbour1 = ArrayList<Int>()
//                val currentNeighbour2 = ArrayList<Int>()
//                val currentNeighbour3 = ArrayList<Int>()
//
//                currentNeighbour1.add(row)
//                currentNeighbour1.add(column + 1)
//                neighbours.add(currentNeighbour1)
//
//                currentNeighbour2.add(row + 1)
//                currentNeighbour2.add(column)
//                neighbours.add(currentNeighbour2)
//
//                currentNeighbour3.add(row)
//                currentNeighbour3.add(column - 1)
//                neighbours.add(currentNeighbour3)
//
//                return neighbours
//
//            }
//            else -> { //row == 7      bottom side
//                val neighbours = ArrayList<List<Int>>()
//                val currentNeighbour1 = ArrayList<Int>()
//                val currentNeighbour2 = ArrayList<Int>()
//                val currentNeighbour3 = ArrayList<Int>()
//
//                currentNeighbour1.add(row - 1)
//                currentNeighbour1.add(column)
//                neighbours.add(currentNeighbour1)
//
//                currentNeighbour2.add(row)
//                currentNeighbour2.add(column + 1)
//                neighbours.add(currentNeighbour2)
//
//                currentNeighbour3.add(row)
//                currentNeighbour3.add(column - 1)
//                neighbours.add(currentNeighbour3)
//
//                return neighbours
//            }
//        }
//    } else {    //around the middle
//        val neighbours = ArrayList<List<Int>>()
//        val currentNeighbour1 = ArrayList<Int>()
//        val currentNeighbour2 = ArrayList<Int>()
//        val currentNeighbour3 = ArrayList<Int>()
//        val currentNeighbour4 = ArrayList<Int>()
//
//        currentNeighbour1.add(row - 1)
//        currentNeighbour1.add(column)
//        neighbours.add(currentNeighbour1)
//
//        currentNeighbour2.add(row)
//        currentNeighbour2.add(column + 1)
//        neighbours.add(currentNeighbour2)
//
//        currentNeighbour3.add(row + 1)
//        currentNeighbour3.add(column)
//        neighbours.add(currentNeighbour3)
//
//        currentNeighbour4.add(row)
//        currentNeighbour4.add(column - 1)
//        neighbours.add(currentNeighbour4)
//

    //  }


    private fun explodePut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView) {

    }


}