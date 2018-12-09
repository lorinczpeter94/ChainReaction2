package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import kotlin.properties.Delegates

/**
 * GamePresenter Class
 *  - coordinates the interactions between the viewMatrix elements
 *
 */

class GamePresenter(
    private var iIGameView: IGameView,
    private var activity: Activity,
    private var context: Context,
    menu: Menu,
    private var activePlayer: ActivePlayer,
    private var viewMatrix: Array<Array<CustomImageView>>
) : IGamePresenter, CustomPresenterDelegate {

    private var neighbourManager = NeighbourManager()
    private val vibrationHandler = VibrationHandler(context)
    private var backgroundSelector = BackgroundSelector(context)
    private var playerManager = PlayerManager(activePlayer.getPlayerNumber(), viewMatrix)
    private var lastStep: Array<Array<CustomImageView>>? = null
    private var lastState: Array<Array<CustomImageView>>? = null
    private var lastPlayer: Int = 0
    private var putSucceed: Boolean = false
    private var soundManager = SoundManager(context)

    private var explodeCount: Int by Delegates.observable(0) { _, oldValue, newValue ->
        freezeScreen(newValue)
        Handler().postDelayed(({
            checkGameState(newValue)
            if (oldValue == 0 && newValue == 0 && putSucceed) {
                activePlayer.nextPlayer()
                val playerCircle = activity.findViewById<ImageView>(R.id.playerCircle)
                iIGameView.setOnecircleTop(
                    playerCircle, backgroundSelector.chooseColor(
                        1,
                        activePlayer.getCurrentPlayer()
                    )
                )
                if (explodeCount == 0) {
                    freezeScreen(0)
                }
            }
        }), 400)
    }

    init {
        setDelegate()

        lastStep = Array(GamePresenter.noOfRows) {
            Array(GamePresenter.noOfColumns) {
                CustomImageView(context, activePlayer)
            }
        }

        lastState = Array(GamePresenter.noOfRows) {
            Array(GamePresenter.noOfColumns) {
                CustomImageView(context, activePlayer)
            }
        }

        val item: MenuItem = menu.findItem(R.id.btn_undo)
        item.setOnMenuItemClickListener {
            undo()
            true
        }
    }

    companion object {
        //Static variables
        const val noOfRows: Int = 8
        const val noOfColumns: Int = 6
    }

    private fun setDelegate(){
        /**
         * CustomViewPresenter class with this class through CustomPresenterDelegate
         */
        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                viewMatrix[i][j].viewPresenter?.customPresenterDelegate = this
            }
        }
    }

    private fun checkGameState(explodeCountNewValue: Int) {
        /**
         * Checks if the current player has any more circles, if not, then sets current player to false.
         * Also checks for winner.
         */

        if (explodeCountNewValue == 0) {
            val players = playerManager.getGameState()

            for (i in 1 until players.size) {
                if (players[i] == 0 && activePlayer.getRoundCounter() > activePlayer.getPlayerNumber()) {
                    activePlayer.setPlayerFalse(i)
                }
            }

            if (playerManager.checkWinner() && activePlayer.getRoundCounter() > 1) {
                Toast.makeText(context, "Player ${activePlayer.getWinner()} won!", Toast.LENGTH_SHORT).show()
                activity.finish()
            }

        }
    }

    private fun iterateNeighbours(neighbours: ArrayList<List<Int>>, color: Int){
        /**
         * Iterates through the neighbours and sends a circle to every one of them
         */

        for (i in neighbours) {
            viewMatrix[i[0]][i[1]].circleComeIn(color)
            if (playerManager.checkWinner()){
                return
            }
        }
    }

    private fun undo() {
        /**
         * Undo to previous step
         * Not finished yet
          */
        //TODO: finish the undo (refresh screen)

        activePlayer.setActivePlayer(lastPlayer)
        if (lastStep == null) {
            return
        }
        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                lastStep?.get(i)?.get(j)?.getColor()?.let { viewMatrix[i][j].setColor(it) }
                lastStep?.get(i)?.get(j)?.getNumberOfCircles()?.let { viewMatrix[i][j].setNumberOfCircles(it) }
            }
        }
    }

    //IGamePresenter functions

    override fun idToInt(id: Int): ArrayList<Int> {
        /**
         * Converts a CustomImageView ID to an array of two indexes
         */

        val indexArray = ArrayList<Int>()

        val i = (id % 10) - 1

        val j = ((id / 10) % 10) - 1
        indexArray.add(j)
        indexArray.add(i)
        return indexArray
    }


    //CustomPresenterDelegate functions

    override fun onExplode(id: Int, color: Int, simulator: Boolean) {
        /**
         * This function is called every time there is an explosion:
         *  - simulator and not simulator function:
         *      - if not simulator then updates UI with post delay, also starts the animations
         *      - if simulator, then no post delays, no UI update, only calculating
         *
         *  - vibrates when explode in non simulator mode
         *
         *  - sends a circle to every neighbour by calling iterateNeighbours
         */

        val indexes = idToInt(id)
        val row = indexes[0]
        val column = indexes[1]
        val neighbours: ArrayList<List<Int>> = neighbourManager.getNeighbours(id)
        soundManager.startExplodeSound()

        if (!simulator) {
            val background = backgroundSelector.chooseColor(1, color)

            vibrationHandler.vibrate(200)

            Handler().postDelayed(({

                iIGameView.midAnimation(viewMatrix[row][column], background)
            }), 100)
            Handler().postDelayed(({
                iterateNeighbours(neighbours, color)
            }), 350)

        } else {
            iterateNeighbours(neighbours, color)
        }
    }

    override fun saveLastStep() {
        /**
         * Saves last step of the viewMatrix in lastStep
         */

        lastPlayer = activePlayer.getCurrentPlayer()
        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                lastStep?.get(i)?.get(j)?.setColor(viewMatrix[i][j].getColor())
                lastStep?.get(i)?.get(j)?.setNumberOfCircles(viewMatrix[i][j].getNumberOfCircles())
            }
        }
    }

    override fun saveState() {
        /**
         * Saves current state of viewMatrix into lastState
         */

        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                lastState?.get(i)?.get(j)?.setColor(viewMatrix[i][j].getColor())
                lastState?.get(i)?.get(j)?.setNumberOfCircles(viewMatrix[i][j].getNumberOfCircles())
            }
        }
    }

    override fun resetState() {
        /**
         * Resets the state of the viewMatrix from the lastState
         */

        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                lastState?.get(i)?.get(j)?.getColor()?.let { viewMatrix[i][j].setColor(it) }
                lastState?.get(i)?.get(j)?.getNumberOfCircles()?.let { viewMatrix[i][j].setNumberOfCircles(it) }
            }
        }
    }

    override fun setSimulation(simulation: Boolean) {
        /**
         * Sets every element of the viewMatrix to simulation mode or not simulation mode
         */

        for (i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                viewMatrix[i][j].setSimulation(simulation)
            }
        }
    }

    override fun incExplodeCount() {
        /**
         * Increasing the number of explosions
         */

        this.explodeCount++
    }

    override fun decExplodeCount() {
        /**
         * Decreasing the number of explosions
          */

        this.explodeCount--
    }

    override fun setZeroExplodeCount() {
        /**
         * Sets the explosions counter to 0
         */

        explodeCount = 0
    }

    override fun getCount(): Int {
        /**
         * Returns the explosion counter
         */

        return explodeCount
    }

    override fun putSucceed(succeeded: Boolean) {
        /**
         * Sets putSucceeded to true or false based on the success or fail of the player to put a circle in a cell
         */

        putSucceed = succeeded
    }

    override fun freezeScreen(explodeCountNewValue: Int) {
        /**
         * if the explodeCountNewValue is 1, then freezes the screen, else it defrosts it
         */

        if (explodeCountNewValue == 1) {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
        if (explodeCountNewValue == 0) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    override fun startPutSound() {
        soundManager.startPutSound()
    }
}