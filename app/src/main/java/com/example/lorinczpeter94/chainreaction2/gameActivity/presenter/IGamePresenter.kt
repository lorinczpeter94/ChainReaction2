package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView

interface IGamePresenter {
    fun elementClicked(imageView: ImageView)
}