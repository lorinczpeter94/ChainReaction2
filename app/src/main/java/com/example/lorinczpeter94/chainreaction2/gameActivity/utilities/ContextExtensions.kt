package com.example.lorinczpeter94.chainreaction2.gameActivity.utilities

import android.content.Context

fun Context.toID(iPosition: Int, jPosition: Int): Int {
    return (iPosition + 1) * 10 + jPosition + 1
}