package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.GameObject
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView

class GamePresenter(
    private var iGameView: IGameView,
    private var activity: Activity,
    private var associatedMatrix:Array<Array<GameObject>>,
    private var playerNumber: Int,
    var activePlayer: ActivePlayer): IGamePresenter {





    override fun elementClicked(imageView: ImageView) {
        // Triggered when an element is clicked in a cell
        val row:Int
        val column:Int
        val indexes:ArrayList<Int> = getIndexes(imageView)

        row = indexes[0]
        column = indexes[1]

        if(playerPut(activePlayer.getCurrentPlayer(), row, column, imageView) == true) {
            activePlayer.nextPlayer()
        }

    }

    private fun  playerPut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView):Boolean{
        //current players puts a circle


        if (circlesNumber(row,column) == 0){    // 1st put in the corner
            associatedMatrix[row][column].color = currentPlayer
            iGameView.setOnecircle(imageView, chooseColor(1,currentPlayer))
            associatedMatrix[row][column].circles++
            return true //put succeeded

        } else if (circlesNumber(row, column) == 1){
            return if (isInCorner(row,column) ){
                // TODO robbanas WIP
                if(currentPlayer == associatedMatrix[row][column].color) {
                    explode(row, column, currentPlayer, imageView)
                    true    // TODO return true
                } else
                    false

            }else{
                if(hisCircle(row, column, currentPlayer)) { //if it's the current player's square
                    associatedMatrix[row][column].color = currentPlayer
                    iGameView.setTwoCircles(imageView, chooseColor(2, currentPlayer))
                    associatedMatrix[row][column].circles++
                    true //put succeeded
                } else
                    false
            }
        } else if (circlesNumber(row,column) == 2){
            return if (isOnSide(row, column)){
                // TODO robbanas buggy

                if(currentPlayer == associatedMatrix[row][column].color) {
                    explode(row, column, currentPlayer, imageView)
                    true // TODO return true
                } else
                    false

            } else{
                if(hisCircle(row, column, currentPlayer)) { //if it's the current player's square
                    associatedMatrix[row][column].color = currentPlayer
                    iGameView.setThreeCircles(imageView, chooseColor(3, currentPlayer))
                    associatedMatrix[row][column].circles++
                    true //put succeeded
                } else
                    false
            }
        }else if (circlesNumber(row, column) == 3){
            //TODO: robbanas
            if(currentPlayer == associatedMatrix[row][column].color) {
                explode(row, column, currentPlayer, imageView)
                return true // TODO return true
            } else
                return false
        }
        return false
    }

    private fun hisCircle(row: Int, column: Int, currentPlayer: Int): Boolean{
        return associatedMatrix[row][column].color == currentPlayer
    }

    private fun chooseColor(noOfCircles:Int, currentPlayer: Int):Drawable {
        //chooses background color,  depends on no. of circles and current player
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
            else-> return ContextCompat.getDrawable(iGameView as Context, R.drawable.green_circle1)!!
        }
    }


    private fun explode(row: Int, column: Int, currentPlayer: Int, imageView: ImageView){
        //explodes

        iGameView.setNoCircle(imageView)
        associatedMatrix[row][column].circles = 0
        //var associatedMatrix =Array(8) {Array(6) { GameObject() } }
        var neighbours:ArrayList<List<Int>> = getNeighbours(row, column)
        var newImageView = activity.findViewById<ImageView>(R.id.gameObject01)

        for(i in neighbours){   //iterate through all neighbours
            explodePut(currentPlayer, i[0], i[1], getImageView(i[0], i[1])) //TODO ?
        }



    }
    

    private fun getNeighbours(row: Int, column: Int):ArrayList<List<Int>>{

        var neighbourForRet = ArrayList<List<Int>>()


        if (isInCorner(row, column)){
            if (row == 0 && column == 0){
                //top left corner
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(0)
                currentNeighbour1.add(1)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(1)
                currentNeighbour2.add(0)
                neighbours.add(currentNeighbour2)

                return neighbours

            }else if (row == 0 && column == 5){
                //top right corner
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(1)
                currentNeighbour1.add(5)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(0)
                currentNeighbour2.add(4)
                neighbours.add(currentNeighbour2)

                return neighbours

            } else if(row == 7 && column ==0){
                //bottom left corner
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(6)
                currentNeighbour1.add(0)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(7)
                currentNeighbour2.add(1)
                neighbours.add(currentNeighbour2)

                return neighbours

            } else{ //row == 7 && column == 5
                //bottom right corner
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(7)
                currentNeighbour1.add(4)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(6)
                currentNeighbour2.add(5)
                neighbours.add(currentNeighbour2)

                return neighbours
            }

        } else if (isOnSide(row, column)){
            if(column == 0){    //left side
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()
                var currentNeighbour3 = ArrayList<Int>()

                currentNeighbour1.add(row - 1)
                currentNeighbour1.add(column)
                neighbours.add(currentNeighbour1)   //the upper one

                currentNeighbour2.add(row)
                currentNeighbour2.add(column + 1)
                neighbours.add(currentNeighbour2)   //the right one

                currentNeighbour3.add(row + 1)
                currentNeighbour3.add(column)
                neighbours.add(currentNeighbour3)   //the bottom one

                return neighbours

            } else if(column == 5){ //right side
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()
                var currentNeighbour3 = ArrayList<Int>()

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

            } else if(row == 0){    //top side
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()
                var currentNeighbour3 = ArrayList<Int>()

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

            } else{ //row == 7      bottom side
                var neighbours = ArrayList<List<Int>>()
                var currentNeighbour1 = ArrayList<Int>()
                var currentNeighbour2 = ArrayList<Int>()
                var currentNeighbour3 = ArrayList<Int>()

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
        } else {    //around the middle
            var neighbours = ArrayList<List<Int>>()
            var currentNeighbour1 = ArrayList<Int>()
            var currentNeighbour2 = ArrayList<Int>()
            var currentNeighbour3 = ArrayList<Int>()
            var currentNeighbour4 = ArrayList<Int>()

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
        return  neighbourForRet



    }


    private fun  explodePut(currentPlayer: Int, row: Int, column: Int, imageView: ImageView){
        //current players puts a circle

        if (circlesNumber(row,column) == 0){    // 1st put in the corner
            associatedMatrix[row][column].color = currentPlayer
            iGameView.setOnecircle(imageView, chooseColor(1,currentPlayer))
            associatedMatrix[row][column].circles++

        } else if (circlesNumber(row, column) == 1){
            if (isInCorner(row,column) ){
                // TODO robbanas WIP
                associatedMatrix[row][column].circles = 0   // TODO -2 not 0
                explode(row, column, currentPlayer, imageView)


            }else{
                    associatedMatrix[row][column].color = currentPlayer
                    iGameView.setTwoCircles(imageView, chooseColor(2, currentPlayer))
                    associatedMatrix[row][column].circles++


            }
        } else if (circlesNumber(row,column) == 2){
            if (isOnSide(row, column)){
                // TODO robbanas WIP
                associatedMatrix[row][column].circles = 0   // TODO -3 not 0
                explode(row, column, currentPlayer, imageView)

            } else{
                    associatedMatrix[row][column].color = currentPlayer
                    iGameView.setThreeCircles(imageView, chooseColor(3, currentPlayer))
                    associatedMatrix[row][column].circles++


            }
        } else if(circlesNumber(row, column) == 3){
            associatedMatrix[row][column].circles = 0   // TODO -4 not 0
            explode(row, column, currentPlayer, imageView)
        }
    }

    private fun isInCorner(row:Int, column:Int):Boolean{
        return (row == 0 && column == 0 || row == 0 && column == 5 ||
                row == 7 && column == 0 || row == 7 && column == 5)
    }

    private fun isOnSide(row:Int, column:Int):Boolean{
        return (row in 1..6 && column == 0 || row in 1..6 && column == 5 ||
                column in 1..4 && row == 0 || column in 1..4 && row == 7)
    }

    private fun circlesNumber(row: Int, column: Int):Int{
        //returns number of circles on the current indexes
        return associatedMatrix[row][column].circles
    }

    private fun getImageView(row: Int, column: Int):ImageView{
        return when (row){
            0 -> return when(column){
                0 -> activity.findViewById<ImageView>(R.id.gameObject00)
                1 -> activity.findViewById<ImageView>(R.id.gameObject01)
                2 -> activity.findViewById<ImageView>(R.id.gameObject02)
                3 -> activity.findViewById<ImageView>(R.id.gameObject03)
                4 -> activity.findViewById<ImageView>(R.id.gameObject04)
                5 -> activity.findViewById<ImageView>(R.id.gameObject05)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            1 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject10)
                1 -> activity.findViewById<ImageView>(R.id.gameObject11)
                2 -> activity.findViewById<ImageView>(R.id.gameObject12)
                3 -> activity.findViewById<ImageView>(R.id.gameObject13)
                4 -> activity.findViewById<ImageView>(R.id.gameObject14)
                5 -> activity.findViewById<ImageView>(R.id.gameObject15)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            2 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject20)
                1 -> activity.findViewById<ImageView>(R.id.gameObject21)
                2 -> activity.findViewById<ImageView>(R.id.gameObject22)
                3 -> activity.findViewById<ImageView>(R.id.gameObject23)
                4 -> activity.findViewById<ImageView>(R.id.gameObject24)
                5 -> activity.findViewById<ImageView>(R.id.gameObject25)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            3 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject30)
                1 -> activity.findViewById<ImageView>(R.id.gameObject31)
                2 -> activity.findViewById<ImageView>(R.id.gameObject32)
                3 -> activity.findViewById<ImageView>(R.id.gameObject33)
                4 -> activity.findViewById<ImageView>(R.id.gameObject34)
                5 -> activity.findViewById<ImageView>(R.id.gameObject35)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            4 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject40)
                1 -> activity.findViewById<ImageView>(R.id.gameObject41)
                2 -> activity.findViewById<ImageView>(R.id.gameObject42)
                3 -> activity.findViewById<ImageView>(R.id.gameObject43)
                4 -> activity.findViewById<ImageView>(R.id.gameObject44)
                5 -> activity.findViewById<ImageView>(R.id.gameObject45)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            5 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject50)
                1 -> activity.findViewById<ImageView>(R.id.gameObject51)
                2 -> activity.findViewById<ImageView>(R.id.gameObject52)
                3 -> activity.findViewById<ImageView>(R.id.gameObject53)
                4 -> activity.findViewById<ImageView>(R.id.gameObject54)
                5 -> activity.findViewById<ImageView>(R.id.gameObject55)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            6 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject60)
                1 -> activity.findViewById<ImageView>(R.id.gameObject61)
                2 -> activity.findViewById<ImageView>(R.id.gameObject62)
                3 -> activity.findViewById<ImageView>(R.id.gameObject63)
                4 -> activity.findViewById<ImageView>(R.id.gameObject64)
                5 -> activity.findViewById<ImageView>(R.id.gameObject65)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            7 -> return when(column) {
                0 -> activity.findViewById<ImageView>(R.id.gameObject70)
                1 -> activity.findViewById<ImageView>(R.id.gameObject71)
                2 -> activity.findViewById<ImageView>(R.id.gameObject72)
                3 -> activity.findViewById<ImageView>(R.id.gameObject73)
                4 -> activity.findViewById<ImageView>(R.id.gameObject74)
                5 -> activity.findViewById<ImageView>(R.id.gameObject75)
                else -> activity.findViewById<ImageView>(R.id.gameObject00)
            }
            else -> return activity.findViewById<ImageView>(R.id.gameObject00)
        }
    }

    private fun getIndexes(imageView: ImageView):ArrayList<Int>{
        // Returns indexes of the imageView
        val indexes = ArrayList<Int>()
        when(imageView.id){
            R.id.gameObject00->{
                indexes.add(0)
                indexes.add(0)
            }
            R.id.gameObject01->{
                indexes.add(0)
                indexes.add(1)
            }
            R.id.gameObject02->{
                indexes.add(0)
                indexes.add(2)
            }
            R.id.gameObject03->{
                indexes.add(0)
                indexes.add(3)
            }
            R.id.gameObject04->{
                indexes.add(0)
                indexes.add(4)
            }
            R.id.gameObject05->{
                indexes.add(0)
                indexes.add(5)
            }
            R.id.gameObject10->{
                indexes.add(1)
                indexes.add(0)
            }
            R.id.gameObject11->{
                indexes.add(1)
                indexes.add(1)
            }
            R.id.gameObject12->{
                indexes.add(1)
                indexes.add(2)
            }
            R.id.gameObject13->{
                indexes.add(1)
                indexes.add(3)
            }
            R.id.gameObject14->{
                indexes.add(1)
                indexes.add(4)
            }
            R.id.gameObject15->{
                indexes.add(1)
                indexes.add(5)
            }
            R.id.gameObject20->{
                indexes.add(2)
                indexes.add(0)
            }
            R.id.gameObject21->{
                indexes.add(2)
                indexes.add(1)
            }
            R.id.gameObject22->{
                indexes.add(2)
                indexes.add(2)
            }
            R.id.gameObject23->{
                indexes.add(2)
                indexes.add(3)
            }
            R.id.gameObject24->{
                indexes.add(2)
                indexes.add(4)
            }
            R.id.gameObject25->{
                indexes.add(2)
                indexes.add(5)
            }
            R.id.gameObject30->{
                indexes.add(3)
                indexes.add(0)
            }
            R.id.gameObject31->{
                indexes.add(3)
                indexes.add(1)
            }
            R.id.gameObject32->{
                indexes.add(3)
                indexes.add(2)
            }
            R.id.gameObject33->{
                indexes.add(3)
                indexes.add(3)
            }
            R.id.gameObject34->{
                indexes.add(3)
                indexes.add(4)
            }
            R.id.gameObject35->{
                indexes.add(3)
                indexes.add(5)
            }
            R.id.gameObject40->{
                indexes.add(4)
                indexes.add(0)
            }
            R.id.gameObject41->{
                indexes.add(4)
                indexes.add(1)
            }
            R.id.gameObject42->{
                indexes.add(4)
                indexes.add(2)
            }
            R.id.gameObject43->{
                indexes.add(4)
                indexes.add(3)
            }
            R.id.gameObject44->{
                indexes.add(4)
                indexes.add(4)
            }
            R.id.gameObject45->{
                indexes.add(4)
                indexes.add(5)
            }
            R.id.gameObject50->{
                indexes.add(5)
                indexes.add(0)
            }
            R.id.gameObject51->{
                indexes.add(5)
                indexes.add(1)
            }
            R.id.gameObject52->{
                indexes.add(5)
                indexes.add(2)
            }
            R.id.gameObject53->{
                indexes.add(5)
                indexes.add(3)
            }
            R.id.gameObject54->{
                indexes.add(5)
                indexes.add(4)
            }
            R.id.gameObject55->{
                indexes.add(5)
                indexes.add(5)
            }
            R.id.gameObject60->{
                indexes.add(6)
                indexes.add(0)
            }
            R.id.gameObject61->{
                indexes.add(6)
                indexes.add(1)
            }
            R.id.gameObject62->{
                indexes.add(6)
                indexes.add(2)
            }
            R.id.gameObject63->{
                indexes.add(6)
                indexes.add(3)
            }
            R.id.gameObject64->{
                indexes.add(6)
                indexes.add(4)
            }
            R.id.gameObject65->{
                indexes.add(6)
                indexes.add(5)
            }
            R.id.gameObject70->{
                indexes.add(7)
                indexes.add(0)
            }
            R.id.gameObject71->{
                indexes.add(7)
                indexes.add(1)
            }
            R.id.gameObject72->{
                indexes.add(7)
                indexes.add(2)
            }
            R.id.gameObject73->{
                indexes.add(7)
                indexes.add(3)
            }
            R.id.gameObject74->{
                indexes.add(7)
                indexes.add(4)
            }
            R.id.gameObject75->{
                indexes.add(7)
                indexes.add(5)
            }
        }


        return indexes
    }

}