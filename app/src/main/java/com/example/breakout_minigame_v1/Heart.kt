package com.example.breakout_minigame_v1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Heart (context: Context, x: Int, y: Int) {
    val size = 100
    val heart: Bitmap

    init {
        heart = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.heart),
            size, size, false)
    }
    var posX = x.toFloat()
    var posY = y.toFloat()
    fun draw(canvas: Canvas, paint: Paint)
    {
        canvas?.drawBitmap(heart, posX, posY, paint)
    }
    public fun getHeartSize(): Int
    {
        return size
    }

}