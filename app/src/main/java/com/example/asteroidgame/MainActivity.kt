package com.example.asteroidgame

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.asteroidgame.R
import com.example.firstonkotlin.db.MyDbManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Class
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity() {
    var start1:Boolean = false
    var start2:Boolean = false
    var start3:Boolean = false
    var count = 3
    val myDBManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var anim1 = AnimationUtils.loadAnimation(this, R.anim.mytrans)
        var anim2 = AnimationUtils.loadAnimation(this, R.anim.myscale)
        var anim3 = AnimationUtils.loadAnimation(this, R.anim.mytrans2)
        var anim4 = AnimationUtils.loadAnimation(this, R.anim.mytrans3)
        var anim5 = AnimationUtils.loadAnimation(this, R.anim.mytrans4)
        ivRaceta1.startAnimation(anim3)
        ivRaceta2.startAnimation(anim4)
        ivAst.startAnimation(anim5)
        ivWorld.startAnimation(anim5)

        var tapSound = MediaPlayer.create(this, R.raw.sound_tap)
        var ballSound = MediaPlayer.create(this, R.raw.sound_ball)

        myDBManager.openDb()
        val dataList = myDBManager.readDbData()
        val arguments = intent.extras
        var bool = arguments?.getBoolean("bool")
        if (bool == true) {
            ivRaceta.startAnimation(anim1)
            tvBalls.startAnimation(anim2)
            ballSound.start()
        }
        tvBalls.append(dataList.toString())


        btnLevel1.setOnClickListener {
            startSoundandClickableBtn(tapSound)
            CoroutineScope(Dispatchers.IO).launch {
                startTimer(start1, GameScreen1::class.java)
            }
        }

        btnLevel2.setOnClickListener {
            startSoundandClickableBtn(tapSound)
            CoroutineScope(Dispatchers.IO).launch {
                startTimer(start2, GameScreen2::class.java)
            }
        }

        btnLevel3.setOnClickListener {
            startSoundandClickableBtn(tapSound)
            CoroutineScope(Dispatchers.IO).launch {
                startTimer(start3, GameScreen3::class.java)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        start1 = false
        start2 = false
        start3 = false
    }

    fun startSoundandClickableBtn(tapSound:MediaPlayer){
        tapSound.start()
        btnLevel1.isClickable = false
        btnLevel2.isClickable = false
        btnLevel3.isClickable = false
    }

    suspend fun startTimer(start: Boolean, screen:Class<*>){
        var start = true
        while (start) {
            withContext(Main){
                tvNum.visibility = View.VISIBLE
                tvNum.setText(count.toString())
            }
            count--
            delay(1000)
            if (count == 0) {
                withContext(Main) {
                    tvNum.visibility = View.INVISIBLE
                }
                var intent1 = Intent(this@MainActivity, screen)
                startActivity(intent1)
                start = false
            }
        }
    }
}