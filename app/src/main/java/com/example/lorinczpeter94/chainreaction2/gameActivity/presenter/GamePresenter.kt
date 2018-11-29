package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView


class GamePresenter(
    private var iGameView: IGameView,
    private var activity: Activity,
    private var activePlayer: ActivePlayer
) : IGamePresenter {
    private var playerManager = PlayerManager(1, iGameView.getMatrixInstance())
    private var associatedMatrix = iGameView.getMatrixInstance()
    private var positionManager = PositionManager(associatedMatrix.getHeight() + 1, associatedMatrix.getWidth() + 1)
    private var round = 0
    override fun elementClicked(imageView: ImageView) {
        // Triggered when an element is clicked in a cell
        round++

        val indexes: ArrayList<Int> = getIndexes(imageView)
        val row: Int = indexes[0]
        val column: Int = indexes[1]

        //calls playerPut - it will place a circle if the [row][column] is correct
        if (playerPut(activePlayer.getCurrentPlayer(), row, column, imageView)) {
            playerManager = PlayerManager(activePlayer.getCurrentPlayer(), associatedMatrix)

            activePlayer.nextPlayer()  //Game goes to next players only if the current players managed to place a circle


            while (!playerManager.checkPlayer(activePlayer.getCurrentPlayer()) && round != 1) {
                activePlayer.nextPlayer()
            }


        //Current Player color on top
        val playerCircle = activity.findViewById<ImageView>(R.id.playerCircle)
        iGameView.setOnecircle(playerCircle, chooseColor(1, activePlayer.getCurrentPlayer()))

            if (playerManager.checkWinner() && round != 1) {
                Toast.makeText(activity, "Player ${activePlayer.getCurrentPlayer()} won!", Toast.LENGTH_LONG).show()
                //activity.finish()
            }


    }


}

private fun playerPut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView): Boolean {
    //current players puts a circle


    when {
        associatedMatrix.getCircles(row, column) == 0 -> {    // 1st put in the corner
            associatedMatrix.setColor(row, column, currentPlayer)
            iGameView.setOnecircle(imageView, chooseColor(1, currentPlayer))
            associatedMatrix.incCircles(row, column)




            //Set Animation on circles in corner
            if (positionManager.isInCorner(row, column)) {
                iGameView.setActiveGameObject(imageView)
            }
            return true //put succeeded

        }
        associatedMatrix.getCircles(row, column) == 1 -> return if (positionManager.isInCorner(row, column)) {
            if (currentPlayer == associatedMatrix.getColor(row, column)) {
                explode(row, column, currentPlayer, imageView) //second circle in corner - explode
                true //put succeeded
            } else

                false

        } else {
            if (associatedMatrix.hisCircle(row, column, currentPlayer)) { //if it's the current players's square
                associatedMatrix.setColor(row, column, currentPlayer)
                iGameView.setTwoCircles(imageView, chooseColor(2, currentPlayer))
                associatedMatrix.incCircles(row, column)

                //Sets rotation animation on side circles
                if (positionManager.isOnSide(row, column)) {
                    iGameView.setActiveGameObject(imageView)
                }
                true //put succeeded
            } else
                false
        }
        associatedMatrix.getCircles(row, column) == 2 -> return if (positionManager.isOnSide(row, column)) {

            if (currentPlayer == associatedMatrix.getColor(row, column)) {
                explode(row, column, currentPlayer, imageView) //third circle on side - explode
                true //put succeeded
            } else
                false

        } else {
            if (associatedMatrix.hisCircle(row, column, currentPlayer)) { //if it's the current players's square
                associatedMatrix.setColor(row, column, currentPlayer)
                iGameView.setThreeCircles(imageView, chooseColor(3, currentPlayer))
                associatedMatrix.incCircles(row, column)
                if (!positionManager.isOnSide(row, column) && !positionManager.isInCorner(row, column)) {
                    iGameView.setActiveGameObject(imageView)
                }
                true //put succeeded

            } else
                false
        }
        associatedMatrix.getCircles(row, column) == 3 -> return if (currentPlayer == associatedMatrix.getColor(
                row,
                column
            )
        ) {
            explode(row, column, currentPlayer, imageView) //4th circle on mid - explode
            true
        } else
            false
        else -> return false
    }
}


private fun chooseColor(noOfCircles: Int, currentPlayer: Int): Drawable {
    //chooses background color,  depends on no. of circles and current players
    when (currentPlayer) {
        1 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.red_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.red_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.red_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.red_circle1)!!
        }
        2 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        3 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.blue_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.blue_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.blue_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        4 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.yellow_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.yellow_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.yellow_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        5 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.purple_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.purple_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.purple_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        6 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.cyan_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.cyan_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.cyan_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        7 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.orange_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.orange_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.orange_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        8 -> return when (noOfCircles) {
            1 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.white_circle1)!!
            }
            2 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.white_circle2)!!
            }
            3 -> {
                ContextCompat.getDrawable(iGameView as Context, R.drawable.white_circle3)!!
            }
            else -> ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
        else -> return ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
    }
}

private fun explode(row: Int, column: Int, currentPlayer: Int, imageView: ImageView) {
    //chain reaction

    iGameView.setNoCircle(imageView)
    associatedMatrix.setZeroCircles(row, column)

    iGameView.stopActiveGameObject(imageView)


    //Gets the neighbours of the circle that has to explode
    val neighbours: ArrayList<List<Int>> = getNeighbours(row, column)


    for (i in neighbours) {   //iterate through all neighbours
        //call explode put with a delay of 300millis


        //TEST ANIMATION
        toBeAnimated(imageView, currentPlayer)
        Handler().postDelayed(({ this.explodePut(currentPlayer, i[0], i[1], getImageView(i[0], i[1])) }), 300)




    }


}

private fun getNeighbours(row: Int, column: Int): ArrayList<List<Int>> {
    //returns the indexes of the neighbours of circle on indexes [row][column]

    if (positionManager.isInCorner(row, column)) {
        if (row == 0 && column == 0) {
            //top left corner
            val neighbours = ArrayList<List<Int>>()
            val currentNeighbour1 = ArrayList<Int>()
            val currentNeighbour2 = ArrayList<Int>()

            currentNeighbour1.add(0)
            currentNeighbour1.add(1)
            neighbours.add(currentNeighbour1)

            currentNeighbour2.add(1)
            currentNeighbour2.add(0)
            neighbours.add(currentNeighbour2)

            return neighbours

        } else if (row == 0 && column == 5) {
            //top right corner
            val neighbours = ArrayList<List<Int>>()
            val currentNeighbour1 = ArrayList<Int>()
            val currentNeighbour2 = ArrayList<Int>()

            currentNeighbour1.add(1)
            currentNeighbour1.add(5)
            neighbours.add(currentNeighbour1)

            currentNeighbour2.add(0)
            currentNeighbour2.add(4)
            neighbours.add(currentNeighbour2)

            return neighbours

        } else if (row == 7 && column == 0) {
            //bottom left corner
            val neighbours = ArrayList<List<Int>>()
            val currentNeighbour1 = ArrayList<Int>()
            val currentNeighbour2 = ArrayList<Int>()

            currentNeighbour1.add(6)
            currentNeighbour1.add(0)
            neighbours.add(currentNeighbour1)

            currentNeighbour2.add(7)
            currentNeighbour2.add(1)
            neighbours.add(currentNeighbour2)

            return neighbours

        } else { //row == 7 && column == 5
            //bottom right corner
            val neighbours = ArrayList<List<Int>>()
            val currentNeighbour1 = ArrayList<Int>()
            val currentNeighbour2 = ArrayList<Int>()

            currentNeighbour1.add(7)
            currentNeighbour1.add(4)
            neighbours.add(currentNeighbour1)

            currentNeighbour2.add(6)
            currentNeighbour2.add(5)
            neighbours.add(currentNeighbour2)

            return neighbours
        }

    } else if (positionManager.isOnSide(row, column)) {
        when {
            column == 0 -> {    //left side
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()
                val currentNeighbour3 = ArrayList<Int>()

                currentNeighbour1.add(row - 1)
                currentNeighbour1.add(column)
                neighbours.add(currentNeighbour1)   //the top one

                currentNeighbour2.add(row)
                currentNeighbour2.add(column + 1)
                neighbours.add(currentNeighbour2)   //the right one

                currentNeighbour3.add(row + 1)
                currentNeighbour3.add(column)
                neighbours.add(currentNeighbour3)   //the bottom one

                return neighbours

            }
            column == 5 -> { //right side
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()
                val currentNeighbour3 = ArrayList<Int>()

                currentNeighbour1.add(row - 1)
                currentNeighbour1.add(column)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(row + 1)
                currentNeighbour2.add(column)
                neighbours.add(currentNeighbour2)

                currentNeighbour3.add(row)
                currentNeighbour3.add(column - 1)
                neighbours.add(currentNeighbour3)

                return neighbours

            }
            row == 0 -> {    //top side
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()
                val currentNeighbour3 = ArrayList<Int>()

                currentNeighbour1.add(row)
                currentNeighbour1.add(column + 1)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(row + 1)
                currentNeighbour2.add(column)
                neighbours.add(currentNeighbour2)

                currentNeighbour3.add(row)
                currentNeighbour3.add(column - 1)
                neighbours.add(currentNeighbour3)

                return neighbours

            }
            else -> { //row == 7      bottom side
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()
                val currentNeighbour3 = ArrayList<Int>()

                currentNeighbour1.add(row - 1)
                currentNeighbour1.add(column)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(row)
                currentNeighbour2.add(column + 1)
                neighbours.add(currentNeighbour2)

                currentNeighbour3.add(row)
                currentNeighbour3.add(column - 1)
                neighbours.add(currentNeighbour3)

                return neighbours
            }
        }
    } else {    //around the middle
        val neighbours = ArrayList<List<Int>>()
        val currentNeighbour1 = ArrayList<Int>()
        val currentNeighbour2 = ArrayList<Int>()
        val currentNeighbour3 = ArrayList<Int>()
        val currentNeighbour4 = ArrayList<Int>()

        currentNeighbour1.add(row - 1)
        currentNeighbour1.add(column)
        neighbours.add(currentNeighbour1)

        currentNeighbour2.add(row)
        currentNeighbour2.add(column + 1)
        neighbours.add(currentNeighbour2)

        currentNeighbour3.add(row + 1)
        currentNeighbour3.add(column)
        neighbours.add(currentNeighbour3)

        currentNeighbour4.add(row)
        currentNeighbour4.add(column - 1)
        neighbours.add(currentNeighbour4)

        return neighbours
    }
}

private fun explodePut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView) {
    //explosion puts it's circles


    if (associatedMatrix.getCircles(row, column) == 0) {    // 1st put in the corner
        associatedMatrix.setColor(row, column, currentPlayer)
        iGameView.setOnecircle(imageView, chooseColor(1, currentPlayer))
        associatedMatrix.incCircles(row, column)
        //players.add(currentPlayer)
        //Set Animation on circles in corner
        if (positionManager.isInCorner(row, column)) {
            iGameView.setActiveGameObject(imageView)
        }

    } else if (associatedMatrix.getCircles(row, column) == 1) {
        if (positionManager.isInCorner(row, column)) {
            associatedMatrix.setZeroCircles(row, column)
            explode(row, column, currentPlayer, imageView)


        } else {
            associatedMatrix.setColor(row, column, currentPlayer)
            iGameView.setTwoCircles(imageView, chooseColor(2, currentPlayer))
            associatedMatrix.incCircles(row, column)

            if (positionManager.isOnSide(row, column)) { //Animation for side circles
                iGameView.setActiveGameObject(imageView)
            }


        }
    } else if (associatedMatrix.getCircles(row, column) == 2) {
        if (positionManager.isOnSide(row, column)) {
            associatedMatrix.setZeroCircles(row, column)
            explode(row, column, currentPlayer, imageView)

        } else {
            associatedMatrix.setColor(row, column, currentPlayer)
            iGameView.setThreeCircles(imageView, chooseColor(3, currentPlayer))
            associatedMatrix.incCircles(row, column)

            if (!positionManager.isOnSide(row, column) && !positionManager.isInCorner(
                    row,
                    column
                )
            ) { //Animation for mid circles
                iGameView.setActiveGameObject(imageView)
            }

        }
    } else if (associatedMatrix.getCircles(row, column) == 3) {
        associatedMatrix.setZeroCircles(row, column)
        explode(row, column, currentPlayer, imageView)
    }
}

private fun toBeAnimated(imageView: ImageView, currentPlayer: Int) {

    iGameView.midAnimation(imageView, chooseColor(1, currentPlayer))


}

private fun getImageView(row: Int, column: Int): ImageView {
    when (row) {
        0 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject00)
            1 -> activity.findViewById(R.id.gameObject01)
            2 -> activity.findViewById(R.id.gameObject02)
            3 -> activity.findViewById(R.id.gameObject03)
            4 -> activity.findViewById(R.id.gameObject04)
            5 -> activity.findViewById(R.id.gameObject05)
            else -> activity.findViewById(R.id.gameObject00)
        }
        1 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject10)
            1 -> activity.findViewById(R.id.gameObject11)
            2 -> activity.findViewById(R.id.gameObject12)
            3 -> activity.findViewById(R.id.gameObject13)
            4 -> activity.findViewById(R.id.gameObject14)
            5 -> activity.findViewById(R.id.gameObject15)
            else -> activity.findViewById(R.id.gameObject00)
        }
        2 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject20)
            1 -> activity.findViewById(R.id.gameObject21)
            2 -> activity.findViewById(R.id.gameObject22)
            3 -> activity.findViewById(R.id.gameObject23)
            4 -> activity.findViewById(R.id.gameObject24)
            5 -> activity.findViewById(R.id.gameObject25)
            else -> activity.findViewById(R.id.gameObject00)
        }
        3 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject30)
            1 -> activity.findViewById(R.id.gameObject31)
            2 -> activity.findViewById(R.id.gameObject32)
            3 -> activity.findViewById(R.id.gameObject33)
            4 -> activity.findViewById(R.id.gameObject34)
            5 -> activity.findViewById(R.id.gameObject35)
            else -> activity.findViewById(R.id.gameObject00)
        }
        4 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject40)
            1 -> activity.findViewById(R.id.gameObject41)
            2 -> activity.findViewById(R.id.gameObject42)
            3 -> activity.findViewById(R.id.gameObject43)
            4 -> activity.findViewById(R.id.gameObject44)
            5 -> activity.findViewById(R.id.gameObject45)
            else -> activity.findViewById(R.id.gameObject00)
        }
        5 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject50)
            1 -> activity.findViewById(R.id.gameObject51)
            2 -> activity.findViewById(R.id.gameObject52)
            3 -> activity.findViewById(R.id.gameObject53)
            4 -> activity.findViewById(R.id.gameObject54)
            5 -> activity.findViewById(R.id.gameObject55)
            else -> activity.findViewById(R.id.gameObject00)
        }
        6 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject60)
            1 -> activity.findViewById(R.id.gameObject61)
            2 -> activity.findViewById(R.id.gameObject62)
            3 -> activity.findViewById(R.id.gameObject63)
            4 -> activity.findViewById(R.id.gameObject64)
            5 -> activity.findViewById(R.id.gameObject65)
            else -> activity.findViewById(R.id.gameObject00)
        }
        7 -> return when (column) {
            0 -> activity.findViewById(R.id.gameObject70)
            1 -> activity.findViewById(R.id.gameObject71)
            2 -> activity.findViewById(R.id.gameObject72)
            3 -> activity.findViewById(R.id.gameObject73)
            4 -> activity.findViewById(R.id.gameObject74)
            5 -> activity.findViewById(R.id.gameObject75)
            else -> activity.findViewById(R.id.gameObject00)
        }
        else -> return activity.findViewById(R.id.gameObject00)
    }
}

private fun getIndexes(imageView: ImageView): ArrayList<Int> {
    // Returns indexes of the imageView
    val indexes = ArrayList<Int>()
    when (imageView.id) {
        R.id.gameObject00 -> {
            indexes.add(0)
            indexes.add(0)
        }
        R.id.gameObject01 -> {
            indexes.add(0)
            indexes.add(1)
        }
        R.id.gameObject02 -> {
            indexes.add(0)
            indexes.add(2)
        }
        R.id.gameObject03 -> {
            indexes.add(0)
            indexes.add(3)
        }
        R.id.gameObject04 -> {
            indexes.add(0)
            indexes.add(4)
        }
        R.id.gameObject05 -> {
            indexes.add(0)
            indexes.add(5)
        }
        R.id.gameObject10 -> {
            indexes.add(1)
            indexes.add(0)
        }
        R.id.gameObject11 -> {
            indexes.add(1)
            indexes.add(1)
        }
        R.id.gameObject12 -> {
            indexes.add(1)
            indexes.add(2)
        }
        R.id.gameObject13 -> {
            indexes.add(1)
            indexes.add(3)
        }
        R.id.gameObject14 -> {
            indexes.add(1)
            indexes.add(4)
        }
        R.id.gameObject15 -> {
            indexes.add(1)
            indexes.add(5)
        }
        R.id.gameObject20 -> {
            indexes.add(2)
            indexes.add(0)
        }
        R.id.gameObject21 -> {
            indexes.add(2)
            indexes.add(1)
        }
        R.id.gameObject22 -> {
            indexes.add(2)
            indexes.add(2)
        }
        R.id.gameObject23 -> {
            indexes.add(2)
            indexes.add(3)
        }
        R.id.gameObject24 -> {
            indexes.add(2)
            indexes.add(4)
        }
        R.id.gameObject25 -> {
            indexes.add(2)
            indexes.add(5)
        }
        R.id.gameObject30 -> {
            indexes.add(3)
            indexes.add(0)
        }
        R.id.gameObject31 -> {
            indexes.add(3)
            indexes.add(1)
        }
        R.id.gameObject32 -> {
            indexes.add(3)
            indexes.add(2)
        }
        R.id.gameObject33 -> {
            indexes.add(3)
            indexes.add(3)
        }
        R.id.gameObject34 -> {
            indexes.add(3)
            indexes.add(4)
        }
        R.id.gameObject35 -> {
            indexes.add(3)
            indexes.add(5)
        }
        R.id.gameObject40 -> {
            indexes.add(4)
            indexes.add(0)
        }
        R.id.gameObject41 -> {
            indexes.add(4)
            indexes.add(1)
        }
        R.id.gameObject42 -> {
            indexes.add(4)
            indexes.add(2)
        }
        R.id.gameObject43 -> {
            indexes.add(4)
            indexes.add(3)
        }
        R.id.gameObject44 -> {
            indexes.add(4)
            indexes.add(4)
        }
        R.id.gameObject45 -> {
            indexes.add(4)
            indexes.add(5)
        }
        R.id.gameObject50 -> {
            indexes.add(5)
            indexes.add(0)
        }
        R.id.gameObject51 -> {
            indexes.add(5)
            indexes.add(1)
        }
        R.id.gameObject52 -> {
            indexes.add(5)
            indexes.add(2)
        }
        R.id.gameObject53 -> {
            indexes.add(5)
            indexes.add(3)
        }
        R.id.gameObject54 -> {
            indexes.add(5)
            indexes.add(4)
        }
        R.id.gameObject55 -> {
            indexes.add(5)
            indexes.add(5)
        }
        R.id.gameObject60 -> {
            indexes.add(6)
            indexes.add(0)
        }
        R.id.gameObject61 -> {
            indexes.add(6)
            indexes.add(1)
        }
        R.id.gameObject62 -> {
            indexes.add(6)
            indexes.add(2)
        }
        R.id.gameObject63 -> {
            indexes.add(6)
            indexes.add(3)
        }
        R.id.gameObject64 -> {
            indexes.add(6)
            indexes.add(4)
        }
        R.id.gameObject65 -> {
            indexes.add(6)
            indexes.add(5)
        }
        R.id.gameObject70 -> {
            indexes.add(7)
            indexes.add(0)
        }
        R.id.gameObject71 -> {
            indexes.add(7)
            indexes.add(1)
        }
        R.id.gameObject72 -> {
            indexes.add(7)
            indexes.add(2)
        }
        R.id.gameObject73 -> {
            indexes.add(7)
            indexes.add(3)
        }
        R.id.gameObject74 -> {
            indexes.add(7)
            indexes.add(4)
        }
        R.id.gameObject75 -> {
            indexes.add(7)
            indexes.add(5)
        }
    }


    return indexes
}

}