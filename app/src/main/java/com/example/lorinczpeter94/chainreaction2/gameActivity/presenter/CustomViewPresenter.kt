package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.ICustomImageView

class CustomViewPresenter(
    private var iCustomImageView: ICustomImageView,
    private var context: Context,
    private var activity: Activity,
    private var numberOfCircles: Int,
    private var color: Int,
    private var activePlayer: ActivePlayer
) {

    private var positionManager = PositionManager(GamePresenter.noOfRows, GamePresenter.noOfColumns)
    private var backgroundSelector = BackgroundSelector(context)

    fun elementClicked(numberOfCircles: Int, color: Int, id: Int) {

        if (playerPut(id, color)){
            activePlayer.nextPlayer()

            val playerCircle = activity.findViewById<ImageView>(R.id.playerCircle)
            iCustomImageView.setOnecircleTop(playerCircle, backgroundSelector.chooseColor(
                numberOfCircles,
                activePlayer.getCurrentPlayer()
            ))
        }



    }


    fun playerPut(id: Int, color: Int): Boolean {

        when (numberOfCircles) {
            0 -> {
                iCustomImageView.incNumberOfCircles()
                iCustomImageView.setOnecircle(backgroundSelector.chooseColor(
                    numberOfCircles + 1 ,
                    activePlayer.getCurrentPlayer()))
                iCustomImageView.setColor(activePlayer.getCurrentPlayer())

                return true
            }
            1 -> {
                if (positionManager.isInCorner(id)){
                    if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                        //current Game object is in corner
                        //TODO: EXPLODE
                        explode()
                        return true
                    }
                    return false


                } else if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())){
                    iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                    iCustomImageView.incNumberOfCircles()
                    iCustomImageView.setTwoCircles(backgroundSelector.chooseColor(
                        numberOfCircles + 1,
                        activePlayer.getCurrentPlayer()
                    ))
                    return true
                } else{
                    return false
                }
            }
            2 -> {
                //current Game object is on side
                if (positionManager.isOnSide(id)){
                    if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())){
                        //TODO: EXPLODE
                        explode()
                        return true
                    } else {
                        return false
                    }

                } else {
                    if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) { //if it's the current player's square
                        iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                        iCustomImageView.setThreeCircles(backgroundSelector.chooseColor(
                            numberOfCircles + 1,
                            activePlayer.getCurrentPlayer()
                        ))
                        iCustomImageView.incNumberOfCircles()
                        if (!positionManager.isOnSide(id) && !positionManager.isInCorner(id)) {
                            //iGameView.setActiveGameObject(imageView)
                        }
                        return true //put succeeded

                    } else
                        return false
                }
            }
            3 -> {
                if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())){
                    explode()
                    return true
                } else {
                    return false
                }
            }
            else -> {
                return false
            }
        }
    }


  fun explode(){}


}