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
) : IGamePresenter {
    companion object {
        val noOfRows: Int = 8
        val noOfColumns: Int = 6

    }

    override fun elementClicked(imageView: ImageView) {
    }




}