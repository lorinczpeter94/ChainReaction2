package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.lorinczpeter94.chainreaction2.R
import com.example.lorinczpeter94.chainreaction2.gameActivity.utilities.toID

class GameLayout(
    var context: Context,
    var layout: RelativeLayout,
    private var viewMatrix: Array<Array<CustomImageView>>
) {

    fun createLayout() {

        for (i in 0 until 8) {
            for (j in 0 until 6) {
                putElementOnLayout(viewMatrix[i][j], i, j)
            }
        }
        for(i in 0 .. 7){
            putHorizontalGridLine(i)
        }
        for (i in 0 .. 5){
            putVerticalGridLine(i)
        }
        putBottomLine()
        putRightLine()

    }

    private fun putRightLine() {
        val view = View(context)
        val lineWidth = 2
        val viewParams = RelativeLayout.LayoutParams(
            lineWidth,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.gridColor))
        viewParams.addRule(RelativeLayout.ALIGN_TOP, toID(0, 2))
        viewParams.addRule(RelativeLayout.ALIGN_RIGHT, toID(0, 5))
        viewParams.addRule(RelativeLayout.ALIGN_BOTTOM, toID(7, 0))
        view.layoutParams = viewParams
        layout.addView(view)
    }

    private fun putBottomLine() {
        val view = View(context)
        val lineWidth = 2
        val viewParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            lineWidth
        )
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.gridColor))
        viewParams.addRule(RelativeLayout.ALIGN_BOTTOM, toID(7, 2))
        viewParams.addRule(RelativeLayout.ALIGN_LEFT, toID(0, 0))
        viewParams.addRule(RelativeLayout.ALIGN_RIGHT, toID(0, 5))
        view.layoutParams = viewParams
        layout.addView(view)
    }

    private fun putHorizontalGridLine(iPosition: Int) {
        val view = View(context)
        val lineWidth = 2
        val viewParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            lineWidth
        )
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.gridColor))
        viewParams.addRule(RelativeLayout.ALIGN_TOP, toID(iPosition, 2))
        viewParams.addRule(RelativeLayout.ALIGN_LEFT, toID(0, 0))
        viewParams.addRule(RelativeLayout.ALIGN_RIGHT, toID(0, 5))
        view.layoutParams = viewParams
        layout.addView(view)
    }

    private fun putVerticalGridLine(iPosition: Int){
        val view = View(context)
        val lineWidth = 2
        val viewParams = RelativeLayout.LayoutParams(
            lineWidth,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.gridColor))
        viewParams.addRule(RelativeLayout.ALIGN_TOP, toID(0, 2))
        viewParams.addRule(RelativeLayout.ALIGN_LEFT, toID(0, iPosition))
        viewParams.addRule(RelativeLayout.ALIGN_BOTTOM, toID(7, 0))
        view.layoutParams = viewParams
        layout.addView(view)
    }

    private fun putElementOnLayout(imageView: CustomImageView, i: Int, j: Int) {
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        imageView.layoutParams = layoutParams
        imageView.id = toID(i, j)

        if (i == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        }

        if (j == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        }

        if (i != 0) {
            layoutParams.addRule(RelativeLayout.BELOW, toID(i - 1, j))
        }

        if (j != 0) {
            layoutParams.addRule(RelativeLayout.RIGHT_OF, toID(i, j - 1))
        }

        layout.addView(imageView)
    }


}

