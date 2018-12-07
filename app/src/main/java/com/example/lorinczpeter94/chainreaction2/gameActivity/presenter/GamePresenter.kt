package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.model.ActivePlayer
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView
import com.example.lorinczpeter94.chainreaction2.gameActivity.view.IGameView
import kotlin.properties.Delegates


class GamePresenter(
    private var iIGameView: IGameView,
    private var activity: Activity,
    private var context: Context,
    private var menu: Menu,
    private var activePlayer: ActivePlayer,
    private var viewMatrix: Array<Array<CustomImageView>>) : IGamePresenter, CustomPresenterDelegate {

    companion object {
        const val noOfRows: Int = 8
        const val noOfColumns: Int = 6

    }
    var neighbourManager = NeighbourManager()
    private var backgroundSelector = BackgroundSelector(context)
    private var playerManager = PlayerManager(activePlayer.getPlayerNumber(), viewMatrix)
    private var lastStep: Array<Array<CustomImageView>>? = null
    private var lastState: Array<Array<CustomImageView>>? = null
    private var lastPlayer: Int = 0

    private var explodeCount: Int by Delegates.observable(0) { _, _, newValue ->
        lockScreen(newValue)
        Handler().postDelayed(({
            checkGameState(newValue)
        }), 400)
    }


    init {
        println("Game presenter init called")
        for (i in 0 until noOfRows){
            for (j in 0 until noOfColumns){
                viewMatrix[i][j].viewPresenter?.customPresenterDelegate = this

                println("set delegate")
            }
        }
        lastStep = Array(GamePresenter.noOfRows) {
            Array(GamePresenter.noOfColumns) {
                CustomImageView(context, activity, activePlayer) }
        }

        lastState= Array(GamePresenter.noOfRows) {
            Array(GamePresenter.noOfColumns) {
                CustomImageView(context, activity, activePlayer) }
        }


        val item: MenuItem = menu.findItem(R.id.btn_undo)
        item.setOnMenuItemClickListener {
            undo()
            true
        }
        true
    }

    override fun elementClicked(imageView: ImageView) {}

    override fun getCheckPlayer(currentPlayer: Int): Boolean {
        playerManager.checkWinner()
        if (playerManager.checkPlayer(currentPlayer)) {
            //TODO:
        }
        return false

    }

    fun lockScreen(explodeCountNewValue: Int){
        if (explodeCountNewValue == 1){
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        if (explodeCountNewValue == 0){
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

    fun checkGameState(explodeCountNewValue: Int){
        if (explodeCountNewValue == 0){

            val players = playerManager.checkGameState()

            //Printing players array
            println("Players: ")
            for (i in players){
                println("$i ")
            }

            for (i in 1 until players.size){
                if (players[i] == 0){
                    activePlayer.setIndexToFalse(i)
                }
            }
        }

    }


    override fun incExplodeCount(){
        this.explodeCount++
    }

    override fun decExplodeCount(){
        this.explodeCount--
    }

    override fun setZeroExplodeCount() {
        explodeCount = 0
    }

    override fun getCount():Int {
        return explodeCount
    }



    override fun onExplode(id: Int, color: Int, simulator: Boolean) {




        val indexes = idToInt(id)
        val row = indexes[0]
        val column = indexes[1]
        val neighbours: ArrayList<List<Int>> = neighbourManager.getNeighbours(id)
        if (!simulator) {
            val background = backgroundSelector.chooseColor(1, color)
            Handler().postDelayed(({

                iIGameView.midAnimation(viewMatrix[row][column], background)
            }), 100)
            Handler().postDelayed(({


                for (i in neighbours) {
                    viewMatrix[i[0]][i[1]].circleComeIn(color)
//                    if (playerManager.checkWinner()){
//
//                        Toast.makeText(context, "Player $color won!", Toast.LENGTH_LONG).show()
//                        //activity.finish()
//                    }
                }
            }), 350)

        } else{


            for (i in neighbours) {
                viewMatrix[i[0]][i[1]].circleComeIn(color)
                //TODO: INFINITE LOOP?
            }
        }


        if (simulator) {

            incExplodeCount()
            println("EXPLODECOUNT++: ${getCount()}")
        } else {
            decExplodeCount()
            println("EXPLODECOUNT--: ${getCount()}")
        }






    }

    fun idToInt(id: Int): ArrayList<Int> {
        val indexArray = ArrayList<Int>()

        val i = (id % 10) - 1

        val j = ((id / 10) % 10) - 1
        indexArray.add(j)
        indexArray.add(i)
        return indexArray
    }

    override fun saveState() {
        for(i in 0 until noOfRows){
            for(j in 0 until noOfColumns){
                lastState?.get(i)?.get(j)?.setColor(viewMatrix[i][j].getColor())
                lastState?.get(i)?.get(j)?.setNumberOfCircles(viewMatrix[i][j].getNumberOfCircles())
            }
        }
    }

    override fun resetState() {
        for(i in 0 until noOfRows){
            for(j in 0 until noOfColumns){
                lastState?.get(i)?.get(j)?.getColor()?.let { viewMatrix[i][j].setColor(it) }
                lastState?.get(i)?.get(j)?.getNumberOfCircles()?.let { viewMatrix[i][j].setNumberOfCircles(it) }
            }
        }
    }

    override fun saveLastStep(){
        lastPlayer = activePlayer.getCurrentPlayer()
        for(i in 0 until noOfRows){
            for(j in 0 until noOfColumns){
                lastStep?.get(i)?.get(j)?.setColor(viewMatrix[i][j].getColor());
                lastStep?.get(i)?.get(j)?.setNumberOfCircles(viewMatrix[i][j].getNumberOfCircles())
            }
        }
    }

    override fun setSimulation(simulation: Boolean) {
        for(i in 0 until noOfRows) {
            for (j in 0 until noOfColumns) {
                viewMatrix[i][j]!!.setSimulation(simulation)
            }
        }
    }

    fun undo(){
        activePlayer.setActivePlayer(lastPlayer)
        if(lastStep == null){
            return
        }
        for(i in 0 until noOfRows){
            for(j in 0 until noOfColumns){
                lastStep?.get(i)?.get(j)?.getColor()?.let { viewMatrix[i][j].setColor(it) }
                lastStep?.get(i)?.get(j)?.getNumberOfCircles()?.let { viewMatrix[i][j].setNumberOfCircles(it) }
            }
        }
    }


    override fun printState() {
        println("STATE:")
        for (i in 0 until noOfRows){
            for (j in 0 until  noOfColumns){
                print("${lastState!![i][j].getNumberOfCircles()} ")
            }
            println()
        }
    }

    override fun printViewMatrix() {
        for (i in 0 until noOfRows){
            for (j in 0 until  noOfColumns){
                print("${viewMatrix!![i][j].getNumberOfCircles()} ")
            }
            println()
        }
    }
}




//getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);