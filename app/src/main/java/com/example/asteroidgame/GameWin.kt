package com.example.asteroidgame

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.asteroidgame.R
import kotlinx.android.synthetic.main.activity_game_win.*

class GameWin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_win)

        var tapSound = MediaPlayer.create(this, R.raw.sound_tap)

        val arguments = intent.extras
        var bool = arguments?.getBoolean("bool")
        btnToMain.setOnClickListener {
            tapSound.start()
            var intent = Intent(this@GameWin, MainActivity::class.java)
            intent.putExtra("bool", true)
            startActivity(intent)
        }
    }
}