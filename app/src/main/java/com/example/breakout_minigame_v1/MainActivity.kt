package com.example.breakout_minigame_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playButton = findViewById<Button>(R.id.playButton)
        val quitButton = findViewById<Button>(R.id.quitButton)
        playButton.setOnClickListener()
        {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
        quitButton.setOnClickListener()
        {
            finishAffinity()
        }
    }
}

