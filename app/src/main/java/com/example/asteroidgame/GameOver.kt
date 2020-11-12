package com.example.asteroidgame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.asteroidgame.R
import kotlinx.android.synthetic.main.activity_game_win.*

class GameOver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        var tapSound = MediaPlayer.create(this, R.raw.sound_tap)

        btnToMain.setOnClickListener {
            tapSound.start()
            var intent = Intent(this@GameOver, MainActivity::class.java)
            startActivity(intent)
        }
    }
}