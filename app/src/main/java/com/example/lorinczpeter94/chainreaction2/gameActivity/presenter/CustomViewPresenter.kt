package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.AssociatedMatrix
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.ICustomImageView
import java.time.LocalTime
import java.util.*

interface CustomPresenterDelegate {
    fun onExplode(id: Int, color: Int)
    fun getCheckPlayer(currentPlayer: Int): Boolean
}


class CustomViewPresenter(
    private var iCustomImageView: ICustomImageView,
    context: Context,
    private var activity: Activity
) {


    private var positionManager = PositionManager(GamePresenter.noOfRows, GamePresenter.noOfColumns)
    private var backgroundSelector = BackgroundSelector(context)

    var customPresenterDelegate: CustomPresenterDelegate? = null

    fun elementClicked(numberOfCircles: Int, color: Int, id: Int, activePlayer: ActivePlayer) {


        if (playerPut(id, color, numberOfCircles, activePlayer)) {
            checkForActive(id) //if circles are active
            activePlayer.nextPlayer()

            while (activePlayer.getRoundCounter() >= activePlayer.getPlayerNumber() &&
                customPresenterDelegate!!.getCheckPlayer(activePlayer.getCurrentPlayer())
            ) {
                activePlayer.nextPlayer()
            }


            val playerCircle = activity.findViewById<ImageView>(R.id.playerCircle)
            iCustomImageView.setOnecircleTop(
                playerCircle, backgroundSelector.chooseColor(
                    1,
                    activePlayer.getCurrentPlayer()
                )
            )
        }
    }



    private fun playerPut(id: Int, color: Int, numberOfCircles: Int, activePlayer: ActivePlayer): Boolean {

        when (numberOfCircles) {
            0 -> {
                iCustomImageView.incNumberOfCircles()
                iCustomImageView.setOnecircle(
                    backgroundSelector.chooseColor(
                        numberOfCircles + 1,
                        activePlayer.getCurrentPlayer()
                    )
                )
                iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                return true
            }
            1 -> {
                if (positionManager.isInCorner(id)) {
                    if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                        //current Game object is in corner
                        //TODO: EXPLODE
                        explode(id, activePlayer.getCurrentPlayer())

                        return true
                    }
                    return false


                } else if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                    iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                    iCustomImageView.incNumberOfCircles()
                    iCustomImageView.setOnecircle(
                        backgroundSelector.chooseColor(
                            numberOfCircles + 1,
                            activePlayer.getCurrentPlayer()
                        )
                    )
                    return true
                } else {
                    return false
                }
            }
            2 -> {
                //current Game object is on side
                if (positionManager.isOnSide(id)) {
                    if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                        //TODO: EXPLODE
                        explode(id, activePlayer.getCurrentPlayer())
                        return true
                    } else {
                        return false
                    }

                } else {
                    if (positionManager.hisCircle(
                            color,
                            activePlayer.getCurrentPlayer()
                        )
                    ) { //if it's the current player's square
                        iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                        iCustomImageView.setOnecircle(
                            backgroundSelector.chooseColor(
                                numberOfCircles + 1,
                                activePlayer.getCurrentPlayer()
                            )
                        )
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
                if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                    explode(id, activePlayer.getCurrentPlayer())
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

    fun circleCameIn(color: Int, id: Int) {
        iCustomImageView.incNumberOfCircles()
        iCustomImageView.setColor(color)
        if (shouldExplode(id)) {
            explode(id, color)
        } else {
            iCustomImageView.setOnecircle(backgroundSelector.chooseColor(iCustomImageView.getNumberOfCircles(), color))
        }

    }

    fun explode(id: Int, color: Int) {
        iCustomImageView.setNoCircle()
        iCustomImageView.zeroNumberOfCircles()
        iCustomImageView.stopActiveGameObject()
        customPresenterDelegate?.onExplode(id, color)

    }

    private fun shouldExplode(id: Int): Boolean {
        return if (positionManager.isInCorner(id) && iCustomImageView.getNumberOfCircles() == 2) {
            true
        } else if (positionManager.isOnSide(id) && iCustomImageView.getNumberOfCircles() == 3) {
            true
        } else iCustomImageView.getNumberOfCircles() == 4
    }

    fun checkForActive(id: Int) {
        if (positionManager.isInCorner(id) && iCustomImageView.getNumberOfCircles() == 1 ||
            positionManager.isOnSide(id) && iCustomImageView.getNumberOfCircles() == 2 ||
            iCustomImageView.getNumberOfCircles() == 3
        ) {
            iCustomImageView.setActiveGameObject()
        }
    }

}