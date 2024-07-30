package com.example.breakout_minigame_v1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Ball (private val screenWidth: Int, private val screenHeight: Int){
    var x = Random.nextInt(screenWidth/2f.toInt()).toFloat()
    var y = screenHeight/2f
    var radius = 30f
    var speedx = 5f
    var speedy = 5f

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
        speedy+=increase
    }

}