package com.example.breakout_minigame_v1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), EndGameFragment.EndGameDialogListener,
    NextLevelFragment.NextLevelDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameView(this, null))
    }

    override fun onRestartClick() {
        // Restart the game
        recreate()
    }

    override fun onMainMenuClick() {
        // Go back to main menu
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onNextLevelClick() {
        recreate()
    }
}
