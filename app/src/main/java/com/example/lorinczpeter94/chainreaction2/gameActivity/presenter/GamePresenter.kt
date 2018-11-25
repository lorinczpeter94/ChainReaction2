package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.GameObject
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView

class GamePresenter(internal var iGameView: IGameView,
                    internal var associatedMatrix:Array<Array<GameObject>>,
                    internal var playerNumber: Int): IGamePresenter {
    //var gameObject:GameObject()



    override fun elementClicked(imageView: ImageView) {
        // Triggered when an element is clicked in a cell
        val row:Int
        val column:Int
        val indexes:ArrayList<Int> = getIndexes(imageView)


        row = indexes[0]
        column = indexes[1]



        
        if (circlesNumber(row,column) == 0){
            iGameView.setOnecircle(imageView)
            associatedMatrix[row][column].circles++

        } else if (circlesNumber(row, column) == 1){
            if (isInCorner(row,column)){
                //TODO robbanas

            }else{
                //iGameView.setNoCircle(imageView)
                iGameView.setTwoCircles(imageView)
                associatedMatrix[row][column].circles++
            }
        } else if (circlesNumber(row,column) == 2){
            if (isOnSide(row, column)){
                //TODO robbanas
            } else{
                iGameView.setThreeCircles(imageView)
                associatedMatrix[row][column].circles++
            }
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
        return associatedMatrix[row][column].circles
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