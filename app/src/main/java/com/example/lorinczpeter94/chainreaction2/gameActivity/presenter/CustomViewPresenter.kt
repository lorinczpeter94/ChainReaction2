package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.content.Context
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.ICustomImageView


interface CustomPresenterDelegate {
    fun onExplode(id: Int, color: Int, simulator: Boolean)
    fun saveLastStep()
    fun saveState()
    fun resetState()
    fun setSimulation(simulation: Boolean)
    fun incExplodeCount()
    fun decExplodeCount()
    fun setZeroExplodeCount()
    fun getCount(): Int
    fun putSucceed(succeeded: Boolean)
    fun freezeScreen(explodeCountNewValue: Int)
}

class CustomViewPresenter(
    private var iCustomImageView: ICustomImageView,
    context: Context
) {

    private var positionManager = PositionManager(GamePresenter.noOfRows, GamePresenter.noOfColumns)
    private var backgroundSelector = BackgroundSelector(context)

    //Delegate to reach GamePresenter
    var customPresenterDelegate: CustomPresenterDelegate? = null


    fun elementClicked(numberOfCircles: Int, color: Int, id: Int, activePlayer: ActivePlayer) {
        /**
         * Called every time a cell is clicked/touched
         *  - resets the explode counter
         *  - saves the state
         *  - runs a simulation to count the number of explodes
         *  - puts the actual circle and updates UI
         */

        customPresenterDelegate!!.setZeroExplodeCount()
        customPresenterDelegate!!.saveState()

        //Simulation for explode count
        if (playerPut(id, color, numberOfCircles, activePlayer, true)) {
            customPresenterDelegate!!.putSucceed(true)
            customPresenterDelegate!!.freezeScreen(1)
        } else {
            customPresenterDelegate!!.putSucceed(false)
        }

        customPresenterDelegate!!.resetState()  //Resets state after simulation

        //Actual put with UI update and animations
        if (playerPut(id, color, numberOfCircles, activePlayer, false)) {
            checkForActive(id) //if circles are active
            customPresenterDelegate!!.saveLastStep() //saves last step for undo button
        }

    }

    private fun playerPut(
        id: Int,
        color: Int,
        numberOfCircles: Int,
        activePlayer: ActivePlayer,
        simulation: Boolean
    ): Boolean {
        /**
         * Puts a circle in the cell with the given ID
         * also checks if it has to explode
         */
        customPresenterDelegate!!.setSimulation(simulation)
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
                when {
                    positionManager.isInCorner(id) -> {
                        if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                            //current Game object is in corner
                            //TODO: EXPLODE
                            explode(id, activePlayer.getCurrentPlayer(), simulation)

                            return true
                        }
                        return false
                    }
                    positionManager.hisCircle(color, activePlayer.getCurrentPlayer()) -> {
                        iCustomImageView.setColor(activePlayer.getCurrentPlayer())
                        iCustomImageView.incNumberOfCircles()
                        iCustomImageView.setOnecircle(
                            backgroundSelector.chooseColor(
                                numberOfCircles + 1, activePlayer.getCurrentPlayer()
                            )
                        )
                        return true
                    }
                    else -> return false
                }
            }
            2 -> {
                //current Game object is on side
                if (positionManager.isOnSide(id)) {
                    return if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                        //TODO: EXPLODE
                        explode(id, activePlayer.getCurrentPlayer(), simulation)
                        true
                    } else {
                        false
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
                return if (positionManager.hisCircle(color, activePlayer.getCurrentPlayer())) {
                    explode(id, activePlayer.getCurrentPlayer(), simulation)
                    true
                } else {
                    false
                }
            }
            else -> {
                return false
            }
        }
    }

    fun circleCameIn(color: Int, id: Int, simulation: Boolean) {
        /**
         * Triggered when a circle came in into a cell
         */

        if (!simulation) {
            iCustomImageView.incNumberOfCircles()
            iCustomImageView.setColor(color)
            if (shouldExplode(id)) {
                explode(id, color, simulation)
            } else {
                iCustomImageView.setOnecircle(
                    backgroundSelector.chooseColor(
                        iCustomImageView.getNumberOfCircles(),
                        color
                    )
                )
            }
        } else {
            iCustomImageView.incNumberOfCircles()
            iCustomImageView.setColor(color)
            if (shouldExplode(id)) {
                explode(id, color, simulation)
            }
        }

    }

    private fun explode(id: Int, color: Int, simulation: Boolean) {
        /**
         * - current circle explodes
         * - sets number of circles to zero
         * - stops rotation animation
         * - calls onExplode in the GamePresenter
         * - ui depends on simulation or not
         */

        if (simulation) {
            iCustomImageView.zeroNumberOfCircles()
            customPresenterDelegate!!.incExplodeCount()
        } else {
            customPresenterDelegate!!.decExplodeCount()
            customPresenterDelegate!!.decExplodeCount()

            iCustomImageView.setNoCircle()
            iCustomImageView.zeroNumberOfCircles()
            iCustomImageView.stopActiveGameObject()
        }

        customPresenterDelegate?.onExplode(id, color, simulation)
    }

    private fun shouldExplode(id: Int): Boolean {
        /**
         * returns true if the cell with the given id should explode
         * else returns false
         */

        return if (positionManager.isInCorner(id) && iCustomImageView.getNumberOfCircles() == 2) {
            true
        } else if (positionManager.isOnSide(id) && iCustomImageView.getNumberOfCircles() == 3) {
            true
        } else iCustomImageView.getNumberOfCircles() == 4
    }

    fun checkForActive(id: Int) {
        /**
         * checks if the cell with the given id is active
         * if it's active, it adds the rotation animation to it
         */

        if (positionManager.isInCorner(id) && iCustomImageView.getNumberOfCircles() == 1 ||
            positionManager.isOnSide(id) && iCustomImageView.getNumberOfCircles() == 2 ||
            iCustomImageView.getNumberOfCircles() == 3
        ) {
            iCustomImageView.setActiveGameObject()
        }
    }

}