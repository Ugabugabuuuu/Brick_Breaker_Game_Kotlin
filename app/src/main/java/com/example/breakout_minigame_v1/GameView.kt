package com.example.breakout_minigame_v1
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView (context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val screenHeight = context.resources.displayMetrics.heightPixels
    private val screenWidth = context.resources.displayMetrics.widthPixels
    private val ball: Ball = Ball(screenWidth, screenHeight)
    private val bricks = mutableListOf<Brick>()
    private val hearts = mutableListOf<Heart>()
    private val padle: Padle = Padle(context, screenHeight)
    private val background: Bitmap
    private val paint: Paint = Paint()
    private var maxLives = 3;


    init {
        background = BitmapFactory.decodeResource(context.resources, R.drawable.game_background)

        val ofset = 300

        val brickPerRow = 6
        val brickRows = 2
        val tmpBrick: Brick = Brick(context, 0, 0)
        val rowWidth = brickPerRow * tmpBrick.getBrickWidth() + (brickPerRow - 1) * 10
        val startX = (screenWidth - rowWidth) / 2
        for (i in 0 until brickPerRow) {
            for (j in 0 until brickRows) {
                var x = i * (tmpBrick.getBrickWidth() + 10) + startX
                var y = (tmpBrick.getBrickHeight() * j) + ofset
                bricks.add(Brick(context, x, y))
            }
        }
        val tmpHeart: Heart = Heart(context,0,0)
        for(i in 0 until maxLives)
        {
            hearts.add(Heart(context, i* (tmpHeart.size + 20), 50))
        }
    }

     override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawBitmap(background, 0f, 0f, paint)

         padle.draw(canvas, paint)

         ball.draw(canvas,paint)
         paint.color = Color.BLACK
         canvas?.drawRect(0f, 0f, screenWidth.toFloat(), 180f, paint)
        for(brick in bricks)
        {
            brick.draw(canvas, paint)
        }
        for(heart in hearts)
        {
            heart.draw(canvas, paint)
        }
         invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            padle.x = event.x - (padle.width / 2)
        }
        return true
    }
}