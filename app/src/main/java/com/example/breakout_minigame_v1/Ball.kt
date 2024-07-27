package com.example.breakout_minigame_v1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Ball (private val screenWidth: Int, private val screenHeight: Int){
    var x = screenWidth/2f
    var y = screenHeight/2f
    var radius = 30f
    var speed = 10f

    fun draw(canvas: Canvas?, paint: Paint)
    {
        paint.color = Color.GREEN

        canvas?.drawCircle(x, y, radius, paint)
    }
    fun update()
    {
        x+=speed
        y+=speed

        if (x <= radius || x >= screenWidth - radius) {
            speed = -speed
        }
        if (y <= radius) {
            speed = Math.abs(speed)
        }
    }
    fun increaseSped(increase: Int)
    {
        speed+=increase
    }

}