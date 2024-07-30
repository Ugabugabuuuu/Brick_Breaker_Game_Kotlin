package com.example.breakout_minigame_v1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.coroutines.coroutineContext

class Padle (context: Context, private val screenHeight: Int) {
    var x = 0f
    var y = 0f
    var height = 60
    var width = 300

    init
    {
        y = (screenHeight-150).toFloat()
        x = (context.resources.displayMetrics.widthPixels / 2 - width / 2).toFloat()
    }
    fun draw(canvas: Canvas, paint: Paint)
    {
        paint.color = Color.WHITE
        canvas?.drawRect(x, y, x+width, y+height, paint)
    }
}