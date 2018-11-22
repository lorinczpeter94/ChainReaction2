package com.example.lorinczpeter94.chainreaction2.welcome_activity.view

interface IWelcomeView {
    fun mapSizeSpinnerInitializer()
    fun playerNumberSpinnerInitializer()
    fun openSmallMapActivity(playerNumber: Int)
    fun openMediumMapActivity(playerNumber: Int)
    fun openLargeMapActivity(playerNumber: Int)
}