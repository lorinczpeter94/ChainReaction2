package com.example.lorinczpeter94.chainreaction2.gameActivity.presenter

import com.example.lorinczpeter94.chainreaction2.gameActivity.view.CustomImageView

class MatrixHandler(
    private var viewMatrix: Array<Array<CustomImageView>>
) {


    fun sendCircles(row: Int, column: Int){
        viewMatrix[row][column].incNumberOfCircles()
    }


}