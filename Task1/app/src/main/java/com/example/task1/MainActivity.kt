package com.example.task1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        // time count down for 30 seconds,
        // with 1 second as countDown interval
        object : CountDownTimer(10000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                textView.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
            }

        }.start()

        startsecondActivity()
    }

    fun startsecondActivity(){
        if(!isDestroyed){
            val intent = Intent(this,exhale::class.java)

            val tmtask = timerTask {
                if (!isDestroyed){
                    startActivity(intent)
                    finish()
                }
            }

            val timer = Timer()

            timer.schedule(tmtask, 10000)

        }
    }

}
