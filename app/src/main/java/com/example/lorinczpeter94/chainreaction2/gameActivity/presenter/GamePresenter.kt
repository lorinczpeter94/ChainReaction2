package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.GameObject
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView

class GamePresenter(internal var iGameView: IGameView,
                    internal var associatedMatrix:Array<Array<Int>>,
                    internal var playerNumber: Int): IGamePresenter {
    //var gameObject:GameObject()


    override fun elementClicked(imageView: ImageView) {
        // Triggered when an element is clicked in a cell

        iGameView.setOnecircle(imageView)

    }

}