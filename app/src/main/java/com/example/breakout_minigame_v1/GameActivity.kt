package com.example.breakout_minigame_v1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), EndGameFragment.EndGameDialogListener,
    NextLevelFragment.NextLevelDialogListener {
    var score : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gameView = GameView(this, null, score)
        setContentView(gameView)
    }

    override fun onRestartClick() {
        score = 0
        recreate()
    }

    override fun onMainMenuClick() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onNextLevelClick() {
        val gameView = GameView(this, null, score)
        setContentView(gameView)
    }
    fun increaseScore(points: Int)
    {
        score+=points
    }
}
