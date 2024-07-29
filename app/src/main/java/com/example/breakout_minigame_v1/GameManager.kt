package com.example.breakout_minigame_v1
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer

class GameManager (
    private val context: Context,
    private val padle: Padle,
    private val bricks: MutableList<Brick>,
    private val ball: Ball,
    private val gameView: GameView,
    private val screenWidth: Int,
    private val screenHeight: Int
){
    private var destroyedBricks = 0
    private var nextLevelShown = false
    private val friction = 1f
    private var mMediaPlayer: MediaPlayer? = null
    private var soundPlaying = false

    fun update()
    {
        if(gameView.gameOver())
            return
        ball.update()
        handleCollision()

    }
    fun handleCollision()
    {
        if(ballPadleColided())
        {
            val hitPosition = (ball.x - padle.x) / padle.width
            val angle = (hitPosition - 0.5f) * Math.PI / 3.0
            ball.speedx  = ball.speedy * Math.sin(angle).toFloat()
            ball.speedy *= -1;
            playSound()
        }

        for(brick in bricks)
        {
            if(ballBrickColided(brick))
            {
                playSound()
                ball.speedy = -ball.speedy
                if (!brick.hit()) {
                    ball.increaseSped(2)
                    destroyedBricks++
                    gameView.increaseScore(1);
                }
                break;
            }
        }


        if(ballFallingOffBottom()) {
            gameView.loseLife()
        }

    }
    fun ballPadleColided(): Boolean
    {
        if(ball.x + ball.radius > padle.x &&
            ball.x - ball.radius < padle.x + padle.width &&
            ball.y + ball.radius > padle.y)
        {
            return true;
        }
        return false;
    }
    fun ballBrickColided(brick: Brick): Boolean
    {
       return  brick.isVisible
               && ball.x + ball.radius > brick.x
               && ball.x - ball.radius < brick.x+brick.getBrickWidth()
               && ball.y + ball.radius > brick.y
               && ball.y - ball.radius < brick.y + brick.getBrickHeight()
    }
    fun ballFallingOffBottom() : Boolean
    {
        return ball.y + ball.radius > screenHeight
    }
    fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(context, R.raw.ball_hit_audio_sound)
            mMediaPlayer?.setOnCompletionListener {
                mMediaPlayer?.reset()
                mMediaPlayer?.release()
                mMediaPlayer = null
            }
        }

        if (mMediaPlayer?.isPlaying == false) {
            mMediaPlayer?.start()
        }
    }
    fun stopSound() {
        mMediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            mMediaPlayer = null
        }
    }
    fun getDestroydBricks() : Int
    {
        return destroyedBricks
    }

}
