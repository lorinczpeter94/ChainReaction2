package com.example.lorinczpeter94.chainreaction2.gameActivity.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.Toast
import com.example.lorinczpeter94.chainreaction2.R
import kotlin.properties.Delegates

class CustomImageView(
    context: Context): ImageView(context) {



    private var color: Int? = 0
    private var numberOfCircles: Int by Delegates.observable(0){
        property, oldValue, newValue -> onPropertyChanged()
    }

    init {
        color = 0
        background = ContextCompat.getDrawable(context, R.drawable.red_circle1)
        this.setOnClickListener {
            onPropertyChanged()
        }

    }




    private fun onPropertyChanged(){
        //TODO( "explode?")
        Toast.makeText(context, "$id touch called", Toast.LENGTH_LONG).show()
    }







}