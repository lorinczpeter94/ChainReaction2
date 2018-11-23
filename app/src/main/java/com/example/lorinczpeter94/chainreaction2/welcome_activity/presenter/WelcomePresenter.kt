package com.example.lorinczpeter94.chainreaction2.welcome_activity.presenter

import com.example.lorinczpeter94.chainreaction2.welcome_activity.model.WelcomeActivitySettings
import com.example.lorinczpeter94.chainreaction2.welcome_activity.view.IWelcomeView

class WelcomePresenter(internal var iWelcomeView: IWelcomeView) : IWelcomePresenter {
    override fun settingsSpinner(mapSize: String, playerNumber: String) {
        //val playerNum: Int = playerNumber[1].toInt()
        //65465651
        val playerNum:Int = playerNumber.take(1).toInt()
        val welcomeActivitySettings = WelcomeActivitySettings(playerNum, mapSize)


        when(welcomeActivitySettings.mapSizeSelected){
            "small" -> iWelcomeView.openSmallMapActivity(welcomeActivitySettings.playersSelected)
            "medium" -> iWelcomeView.openMediumMapActivity(welcomeActivitySettings.playersSelected)
            "large" -> iWelcomeView.openLargeMapActivity(welcomeActivitySettings.playersSelected)
            else -> iWelcomeView.openMediumMapActivity(welcomeActivitySettings.playersSelected)
        }
    }


}