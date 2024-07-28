package com.example.breakout_minigame_v1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import kotlin.random.Random
import com.example.breakout_minigame_v1.R

class Brick(context: Context, var x: Int, var y: Int)  {
    private var width = 160
    private var height = 90
    var isVisible = true;
    var hitCount = 0

    private val brickAssets = arrayOf(
        Pair(R.drawable.breakout_tiles1, R.drawable.breakout_tiles2),
        Pair(R.drawable.breakout_tiles3, R.drawable.breakout_tiles4),
        Pair(R.drawable.breakout_tiles5, R.drawable.breakout_tiles6),
        Pair(R.drawable.breakout_tiles7, R.drawable.breakout_tiles8)
    );

    val intactTileBitmap: Bitmap
    var crackedTileBìtmap: Bitmap
    var currentBitmap: Bitmap

    init
    {
        var randomPair = brickAssets[Random.nextInt(brickAssets.size)]
        intactTileBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.resources, randomPair.first), width, height, false )
        crackedTileBìtmap = BitmapFactory.decodeResource(context.resources, randomPair.second)
        currentBitmap = intactTileBitmap

    }
    fun draw(canvas: Canvas, paint: Paint)
    {
        if(!isVisible)
            return
        val scaledBitmap = Bitmap.createScaledBitmap(currentBitmap, width,height, false)
        canvas?.drawBitmap(scaledBitmap, x.toFloat(), y.toFloat(), paint)
    }
    fun hit(): Boolean {
        hitCount++
        if (hitCount == 1) {
            currentBitmap = crackedTileBìtmap
        } else if (hitCount >= 2) {
            isVisible = false

        }
        return isVisible
    }
    public fun getBrickWidth() : Int
    {
        return width
    }
    public fun getBrickHeight() : Int
    {
        return height
    }

}