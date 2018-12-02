package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

class NeighbourManager {
    private var positionManager = PositionManager(GamePresenter.noOfRows, GamePresenter.noOfColumns)



    fun getNeighbours(id: Int): ArrayList<List<Int>> {
        //returns the indexes of the neighbours of circle on indexes [row][column] based on id
        val indexes = positionManager.idToInt(id)
        val row = indexes[0]
        val column = indexes[1]


        if (positionManager.isInCorner(id)) {
            if (row == 0 && column == 0) {
                //top left corner
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(0)
                currentNeighbour1.add(1)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(1)
                currentNeighbour2.add(0)
                neighbours.add(currentNeighbour2)

                return neighbours

            } else if (row == 0 && column == 5) {
                //top right corner
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(1)
                currentNeighbour1.add(5)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(0)
                currentNeighbour2.add(4)
                neighbours.add(currentNeighbour2)

                return neighbours

            } else if (row == 7 && column == 0) {
                //bottom left corner
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(6)
                currentNeighbour1.add(0)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(7)
                currentNeighbour2.add(1)
                neighbours.add(currentNeighbour2)

                return neighbours

            } else { //row == 7 && column == 5
                //bottom right corner
                val neighbours = ArrayList<List<Int>>()
                val currentNeighbour1 = ArrayList<Int>()
                val currentNeighbour2 = ArrayList<Int>()

                currentNeighbour1.add(7)
                currentNeighbour1.add(4)
                neighbours.add(currentNeighbour1)

                currentNeighbour2.add(6)
                currentNeighbour2.add(5)
                neighbours.add(currentNeighbour2)

                return neighbours
            }

        } else if (positionManager.isOnSide(id)) {
            when {
                column == 0 -> {    //left side
                    val neighbours = ArrayList<List<Int>>()
                    val currentNeighbour1 = ArrayList<Int>()
                    val currentNeighbour2 = ArrayList<Int>()
                    val currentNeighbour3 = ArrayList<Int>()

                    currentNeighbour1.add(row - 1)
                    currentNeighbour1.add(column)
                    neighbours.add(currentNeighbour1)   //the top one

                    currentNeighbour2.add(row)
                    currentNeighbour2.add(column + 1)
                    neighbours.add(currentNeighbour2)   //the right one

                    currentNeighbour3.add(row + 1)
                    currentNeighbour3.add(column)
                    neighbours.add(currentNeighbour3)   //the bottom one

                    return neighbours

                }
                column == 5 -> { //right side
                    val neighbours = ArrayList<List<Int>>()
                    val currentNeighbour1 = ArrayList<Int>()
                    val currentNeighbour2 = ArrayList<Int>()
                    val currentNeighbour3 = ArrayList<Int>()

                    currentNeighbour1.add(row - 1)
                    currentNeighbour1.add(column)
                    neighbours.add(currentNeighbour1)

                    currentNeighbour2.add(row + 1)
                    currentNeighbour2.add(column)
                    neighbours.add(currentNeighbour2)

                    currentNeighbour3.add(row)
                    currentNeighbour3.add(column - 1)
                    neighbours.add(currentNeighbour3)

                    return neighbours

                }
                row == 0 -> {    //top side
                    val neighbours = ArrayList<List<Int>>()
                    val currentNeighbour1 = ArrayList<Int>()
                    val currentNeighbour2 = ArrayList<Int>()
                    val currentNeighbour3 = ArrayList<Int>()

                    currentNeighbour1.add(row)
                    currentNeighbour1.add(column + 1)
                    neighbours.add(currentNeighbour1)

                    currentNeighbour2.add(row + 1)
                    currentNeighbour2.add(column)
                    neighbours.add(currentNeighbour2)

                    currentNeighbour3.add(row)
                    currentNeighbour3.add(column - 1)
                    neighbours.add(currentNeighbour3)

                    return neighbours

                }
                else -> { //row == 7      bottom side
                    val neighbours = ArrayList<List<Int>>()
                    val currentNeighbour1 = ArrayList<Int>()
                    val currentNeighbour2 = ArrayList<Int>()
                    val currentNeighbour3 = ArrayList<Int>()

                    currentNeighbour1.add(row - 1)
                    currentNeighbour1.add(column)
                    neighbours.add(currentNeighbour1)

                    currentNeighbour2.add(row)
                    currentNeighbour2.add(column + 1)
                    neighbours.add(currentNeighbour2)

                    currentNeighbour3.add(row)
                    currentNeighbour3.add(column - 1)
                    neighbours.add(currentNeighbour3)

                    return neighbours
                }
            }
        } else {    //around the middle
            val neighbours = ArrayList<List<Int>>()
            val currentNeighbour1 = ArrayList<Int>()
            val currentNeighbour2 = ArrayList<Int>()
            val currentNeighbour3 = ArrayList<Int>()
            val currentNeighbour4 = ArrayList<Int>()

            currentNeighbour1.add(row - 1)
            currentNeighbour1.add(column)
            neighbours.add(currentNeighbour1)

            currentNeighbour2.add(row)
            currentNeighbour2.add(column + 1)
            neighbours.add(currentNeighbour2)

            currentNeighbour3.add(row + 1)
            currentNeighbour3.add(column)
            neighbours.add(currentNeighbour3)

            currentNeighbour4.add(row)
            currentNeighbour4.add(column - 1)
            neighbours.add(currentNeighbour4)

            return neighbours
        }
    }


}