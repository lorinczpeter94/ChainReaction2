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
    private var activePlayer: ActivePlayer,
    private var viewMatrix: Array<Array<CustomImageView>>
) : IGamePresenter, CustomPresenterDelegate {
    companion object {
        val noOfRows: Int = 8
        val noOfColumns: Int = 6

    }
    var neighbourManager = NeighbourManager()


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


    override fun onExplode(id: Int, color: Int) {
        val neighbours: ArrayList<List<Int>> = neighbourManager.getNeighbours(id)
        for (i in neighbours) {
            viewMatrix[i[0]][i[1]].circleComeIn(color)
        }
    }
}