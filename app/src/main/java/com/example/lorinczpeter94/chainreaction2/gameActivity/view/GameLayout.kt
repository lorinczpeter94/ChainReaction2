package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.lorinczpeter94.chainreaction2.gameActivity.utilities.toID

class GameLayout(
    var context: Context,
    var layout: RelativeLayout,
    var viewMatrix: Array<Array<CustomImageView>>
) {

    fun createLayout() {
        for (i in 0 until 8) {
            for (j in 0 until 6) {
                putElementOnLayout(viewMatrix[i][j], i, j)
            }
        }

    }

    fun putElementOnLayout(imageView: CustomImageView, i: Int, j: Int) {
        var layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.layoutParams = layoutParams
        imageView.id = context.toID(i, j)

        if (i == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)

        }

        if (j == 0) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)

        }

        if (i != 0) {
            layoutParams.addRule(RelativeLayout.BELOW, context.toID(i - 1, j))
        }

        if (j != 0) {
            layoutParams.addRule(RelativeLayout.RIGHT_OF, context.toID(i, j - 1))
        }

        layout.addView(imageView)
    }


}

