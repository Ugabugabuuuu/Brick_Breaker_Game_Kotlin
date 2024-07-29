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
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import kotlin.math.log

class GameView (context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val screenHeight = context.resources.displayMetrics.heightPixels
    private val screenWidth = context.resources.displayMetrics.widthPixels
    private val ball: Ball = Ball(screenWidth, screenHeight)
    private val bricks = mutableListOf<Brick>()
    private val hearts = mutableListOf<Heart>()
    private val padle: Padle = Padle(context, screenHeight)
    private val background: Bitmap
    private val paint: Paint = Paint()
    private val maxLives = 3;
    private var currentLives = maxLives
    private val gameManager: GameManager
    private val topBarZoneOfset = 180f
    private var gameOver = false
    public var score = 0;
    private var scoreReset = false
    init {
        if(scoreReset)
            score = 0
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
        gameManager = GameManager(context, padle, bricks, ball, this, screenWidth, screenHeight)
    }

     override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.drawBitmap(background, 0f, 0f, paint)

         padle.draw(canvas, paint)

         ball.draw(canvas,paint)
         paint.color = Color.BLACK
         canvas?.drawRect(0f, 0f, screenWidth.toFloat(), topBarZoneOfset, paint)
         for(i in 0 until currentLives)
         {
             hearts[i].draw(canvas, paint)
         }
         for(brick in bricks)
        {
            brick.draw(canvas, paint)
        }

         val scorePaint = Paint()
         scorePaint.color = Color.WHITE
         scorePaint.textSize = 64f
         scorePaint.textAlign = Paint.Align.RIGHT
         canvas.drawText("Score: ${score}", screenWidth - 20f, 80f, scorePaint)


         invalidate()
         gameManager.update()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            padle.x = event.x - (padle.width / 2)
        }
        return true
    }
    fun getTopBarZoneOfset() : Float
    {
        return topBarZoneOfset
    }

    fun loseLife() {
        currentLives--
        if (currentLives <= 0) {
            if(!gameOver)
            {
                endGame()
                gameOver = true;
            }

        } else {
            resetBall()
        }
    }

    private fun endGame() {
        ball.speedy = 0f
        ball.speedx = 0f
        scoreReset = true

        val activity = context as FragmentActivity
        val endGameDialog = EndGameFragment.newInstance()
        endGameDialog.show(activity.supportFragmentManager, "endGameDialog")
    }
    public fun nextLevel()
    {
        ball.speedy = 0f
        ball.speedx = 0f
        val activity = context as FragmentActivity
        val nextLevelDialog = NextLevelFragment.newInstance()
        nextLevelDialog.show(activity.supportFragmentManager, "nextLevelFragment")
    }
    fun resetBall()
    {
        ball.x = screenWidth/2f
        ball.y = screenHeight/2f
    }
    fun increaseScore(points: Int) {
        score += points
        invalidate()  // Redraw the view to update the score display
    }
}