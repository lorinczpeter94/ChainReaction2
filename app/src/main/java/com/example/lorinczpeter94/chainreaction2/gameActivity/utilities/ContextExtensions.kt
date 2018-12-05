package com.example.lorinczpeter94.chainreaction2.gameActivity.utilities

import android.content.Context

fun Context.toID(iPosition: Int, jPosition: Int): Int {
    return (iPosition + 1) * 10 + jPosition + 1
}

fun Context.IDtoInt(id:Int):ArrayList<Int>{
    val indexArray = ArrayList<Int>()

    val i = (id % 10) - 1

    val j = ((id / 10) % 10)-1
    indexArray.add(j)
    indexArray.add(i)
    return indexArray
}