package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView


class GamePresenter(
    private var iIGameView: IGameView,
    private var activity: Activity,
    private var context: Context,
    private var activePlayer: ActivePlayer,
    private var viewMatrix: Array<Array<CustomImageView>>) : IGamePresenter, CustomPresenterDelegate {


    companion object {
        val noOfRows: Int = 8
        val noOfColumns: Int = 6
    }
    var neighbourManager = NeighbourManager()
    private var backgroundSelector = BackgroundSelector(context)
    private var playerManager = PlayerManager(activePlayer.getPlayerNumber(), viewMatrix)



    init {
        println("Game presenter init called")
        for (i in 0 until noOfRows){
            for (j in 0 until noOfColumns){
                viewMatrix[i][j].viewPresenter?.customPresenterDelegate = this

                println("set delegate")
            }
        }
    }

    override fun elementClicked(imageView: ImageView) {
    }

    override fun getCheckPlayer(currentPlayer: Int): Boolean {
        playerManager.checkWinner()
        if (playerManager.checkPlayer(currentPlayer)){
            println("TRUE!!!")
            return true
        } else {
            println("FALSE!!!")
            return false
        }
    }

    override fun onExplode(id: Int, color: Int) {
        val indexes = idToInt(id)
        val row = indexes[0]
        val column = indexes[1]
        val background = backgroundSelector.chooseColor(1, color)
        val neighbours: ArrayList<List<Int>> = neighbourManager.getNeighbours(id)
        Handler().postDelayed(({

            iIGameView.midAnimation(viewMatrix[row][column], background)
        }), 100)



        Handler().postDelayed(({
            for (i in neighbours) {
                viewMatrix[i[0]][i[1]].circleComeIn(color)
                if (playerManager.checkWinner()){
                    Toast.makeText(context, "Player $color won!", Toast.LENGTH_LONG).show()
                    activity.finish()
                }
            }
        }), 350)







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