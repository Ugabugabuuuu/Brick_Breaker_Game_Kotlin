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
    var lastX = 0f
    var velocity = 0f
    //val padleAsset: Bitmap

    init
    {
       // padleAsset = BitmapFactory.decodeResource(context.resources, R.drawable.padle)
        //height = padleAsset.height
        //width = padleAsset.width
        y = (screenHeight-150).toFloat()
        x = (context.resources.displayMetrics.widthPixels / 2 - width / 2).toFloat()
    }
    fun draw(canvas: Canvas, paint: Paint)
    {
        paint.color = Color.WHITE
        canvas?.drawRect(x, y, x+width, y+height, paint)
        //canvas?.drawBitmap(padleAsset, x, y, paint)
    }
    fun update()
    {
        velocity = x-lastX
        lastX = x
    }
}