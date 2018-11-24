package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView

class GamePresenter(internal var iGameView: IGameView,
                    internal var associatedMatrix:Array<Array<Int>>,
                    internal var playerNumber: Int): IGamePresenter {
    //var gameObject:GameObject()

    fun getIndexes(imageView: ImageView):ArrayList<Int>?{
        var indexes = ArrayList<Int>()
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

    override fun elementClicked(imageView: ImageView) {
        // Triggered when an element is clicked in a cell
        var index1:Int
        var index2:Int
        var indexes:ArrayList<Int>?

        indexes = getIndexes(imageView)
        if(indexes != null){
            index1 = indexes.get(0)
            index2 = indexes.get(1)
        } else throw NullPointerException()
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

        if(associatedMatrix[index1][index2] == 0) {
            iGameView.setOnecircle(imageView)
            associatedMatrix[index1][index2]++
        } else {
            if (associatedMatrix[index1][index2] == 1) {
                 if(index1 == 0 && index2 == 0 || index1 == 0 && index2 == 5 ||
                        index1 == 7 && index2 == 0 || index1 == 7 && index2 == 5) {
                     // TODO robbanas
                 }else {
                     iGameView.setNoCircle(imageView)
                     iGameView.setTwoCircles(imageView)
                     associatedMatrix[index1][index2]++
                 }
            } else {
                if(index1 == 0 || index2 == 0 || index1 == 7 || index2 == 5){
                    // TODO robbanas
                } else {
                    iGameView.setNoCircle(imageView)
                    iGameView.setThreeCircles(imageView)
                    associatedMatrix[index1][index2]++
                }
            }
        }
    }

}