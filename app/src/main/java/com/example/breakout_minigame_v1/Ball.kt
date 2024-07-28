package com.example.breakout_minigame_v1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Ball (private val screenWidth: Int, private val screenHeight: Int){
    var x = screenWidth/2f
    var y = screenHeight/2f
    var radius = 30f
    var speedx = 10f
    var speedy = 10f

    fun draw(canvas: Canvas?, paint: Paint)
    {
        paint.color = Color.GREEN

        canvas?.drawCircle(x, y, radius, paint)
    }
    fun update()
    {
        x+=speedx
        y+=speedy

        if (x <= radius || x >= screenWidth - radius) {
            speedx = -speedx
        }
        if (y < 180) {
            speedy= -speedy
        }
    }
    fun increaseSped(increase: Int)
    {
        //speedx+=increase
        speedy+=increase
    }

}