package com.example.breakout_minigame_v1

class GameManager (
    private val padle: Padle,
    private val bricks: MutableList<Brick>,
    private val ball: Ball,
    private val gameView: GameView,
    private val screenWidth: Int,
    private val screenHeight: Int
){
    fun update()
    {
        ball.update()
        handleCollision()

    }
    fun handleCollision()
    {
        if(ballPadleColided())
        {
            ball.speedy = -Math.abs(ball.speedy)
        }

        for(brick in bricks)
        {
            if(ballBrickColided(brick))
            {
                ball.speedy = -ball.speedy
                ball.y = ball.y + brick.getBrickHeight()
                if (!brick.hit()) {
                    ball.increaseSped(2)
                }
                break;
            }
        }
        if(ballFallingOffBottom())
        {
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


}
