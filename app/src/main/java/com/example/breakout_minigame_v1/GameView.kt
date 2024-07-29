package com.example.breakout_minigame_v1

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentActivity

class GameView(context: Context, attrs: AttributeSet?, var score: Int) : View(context, attrs) {
    private val screenHeight = context.resources.displayMetrics.heightPixels
    private val screenWidth = context.resources.displayMetrics.widthPixels
    private val ball: Ball = Ball(screenWidth, screenHeight)
    private val bricks = mutableListOf<Brick>()
    private val hearts = mutableListOf<Heart>()
    private val padle: Padle = Padle(context, screenHeight)
    private val background: Bitmap
    private val paint: Paint = Paint()
    private val maxLives = 3
    private var currentLives = maxLives
    private lateinit var gameManager: GameManager
    private val topBarZoneOffset = 180f
    private var gameOver = false
    private var nextLevelShown = false
    private val brickPerRow = 6
    private val brickRows = 2
    init {
        background = BitmapFactory.decodeResource(context.resources, R.drawable.game_background)
        initializeGame()
    }

    private fun initializeGame(resetScore: Boolean = false) {
        currentLives = maxLives
        bricks.clear()
        val offset = 300
        val tmpBrick = Brick(context, 0, 0)
        val rowWidth = brickPerRow * tmpBrick.getBrickWidth() + (brickPerRow - 1) * 10
        val startX = (screenWidth - rowWidth) / 2

        for (i in 0 until brickPerRow) {
            for (j in 0 until brickRows) {
                val x = startX + i * (tmpBrick.getBrickWidth() + 10)
                val y = offset + j * (tmpBrick.getBrickHeight() + 10)
                bricks.add(Brick(context, x, y))
            }
        }

        hearts.clear()
        val tmpHeart = Heart(context, 0, 0)
        for (i in 0 until maxLives) {
            hearts.add(Heart(context, i * (tmpHeart.size + 20), 50))
        }

        gameManager = GameManager(context, padle, bricks, ball, this, screenWidth, screenHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(background, 0f, 0f, paint)

        padle.draw(canvas, paint)
        ball.draw(canvas, paint)

        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, screenWidth.toFloat(), topBarZoneOffset, paint)

        for (brick in bricks) {
            brick.draw(canvas, paint)
        }

        for (i in 0 until currentLives) {
            hearts[i].draw(canvas, paint)
        }

        val scorePaint = Paint().apply {
            color = Color.WHITE
            textSize = 64f
            textAlign = Paint.Align.RIGHT
        }
        canvas.drawText("Score: $score", screenWidth - 20f, 100f, scorePaint)

        if (gameManager.getDestroydBricks() == brickRows*brickPerRow && !nextLevelShown) {
            showNextLevelDialog()
            nextLevelShown = true
        }

        gameManager.update()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            padle.x = event.x - (padle.width / 2)
        }
        return true
    }

    fun loseLife() {
        currentLives--
        if (currentLives <= 0 && !gameOver) {
            showEndGameDialog()
            gameOver = true
        } else {
            resetBall()
        }
    }

    private fun resetBall() {
        ball.x = screenWidth / 2f
        ball.y = screenHeight / 3f
    }

    private fun showEndGameDialog() {
        ball.speedy = 0f
        ball.speedx = 0f

        val activity = context as FragmentActivity
        val endGameDialog = EndGameFragment.newInstance()
        endGameDialog.show(activity.supportFragmentManager, "endGameDialog")
        gameOver = true
    }

    private fun showNextLevelDialog() {
        ball.speedy = 0f
        ball.speedx = 0f

        val activity = context as FragmentActivity
        val nextLevelDialog = NextLevelFragment.newInstance()
        nextLevelDialog.show(activity.supportFragmentManager, "nextLevelFragment")

        nextLevelShown = true
    }

    fun nextLevel() {

        resetBall()
        initializeGame(resetScore = false)
        invalidate()
    }

    fun increaseScore(points: Int) {
        score += points
        (context as GameActivity).increaseScore(points)
    }

    fun gameOver(): Boolean {
        return gameOver
    }
}
