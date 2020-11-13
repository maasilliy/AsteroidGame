package com.example.asteroidgame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.asteroidgame.R
import com.example.firstonkotlin.db.MyDbManager
import kotlinx.android.synthetic.main.activity_game_screen2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class GameScreen1 : AppCompatActivity() {
    var start:Boolean = false
    var con:Boolean = false
    var count:Int = 30
    val myDBManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        var damageSound = MediaPlayer.create(this, R.raw.sound_damage)
        var winSound = MediaPlayer.create(this, R.raw.sound_win)
        var overSound = MediaPlayer.create(this, R.raw.sound_over)

        //Corotinues Timer
        CoroutineScope(Dispatchers.IO).launch {
            Timer(winSound)
        }

        //Corotinues DamageToAsteroid
        CoroutineScope(Dispatchers.IO).launch {
            damageToAsteroid(overSound)
        }

        ibAst1.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire1, ibAst1)
        }
        ibAst2.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire2, ibAst2)
        }
        ibAst3.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire3, ibAst3)
        }
        ibAst4.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire4, ibAst4)
        }
        ibAst5.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire5, ibAst5)
        }
        ibAst6.setOnClickListener{
            clickOnAsteroid(damageSound, ivFire6, ibAst6)
        }

    }

    override fun onDestroy() {
        myDBManager.closeDb()
        super.onDestroy()
        start = false
        con = false
    }

    fun clickOnAsteroid(damageSound:MediaPlayer, ivFire:ImageView, ibAst:ImageButton){
        damageSound.start()
        ivFire.visibility = View.VISIBLE
        ibAst.visibility = View.INVISIBLE
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable { ivFire.visibility = View.INVISIBLE },
            200
        )
    }

     suspend fun Timer(winSound:MediaPlayer){
         con = true
         while (con) {
             withContext(Main) {
                 tvTime.setText(count.toString())
             }
             count--
             delay(1000)
             if (count <= 0) {
                 myDBManager.openDb()
                 myDBManager.insertToDb(1)
                 winSound.start()
                 con = false
                 start = false
                 var intent = Intent(this@GameScreen1, GameWin::class.java)
                 intent.putExtra("bool", true)
                 startActivity(intent)
             }
         }
    }

    suspend fun damageToAsteroid(overSound: MediaPlayer) {
        var array = mutableListOf<ImageButton>(ibAst1, ibAst2, ibAst3, ibAst4, ibAst5, ibAst6)
        start = true
        while (start) {
            withContext(Dispatchers.Main) {
                array.random().visibility = View.VISIBLE
            }
            delay(400)
            if (array.all {it.visibility == View.VISIBLE})  {
                overSound.start()
                con = false
                start = false
                val intent = Intent(this@GameScreen1, GameOver::class.java)
                startActivity(intent)
            }
        }
    }
}