package ru.utsx.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Objects

class TimeActivity : AppCompatActivity() {
    private var score = 0
    private var speed = 1L
    private var playerScore: com.google.android.material.textview.MaterialTextView? = null
    private var name = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = intent.getStringExtra("name").toString()
        println("Time = $name")
        setContentView(R.layout.activity_time)

        playerScore = findViewById(R.id.score)
        playGame()
    }

    private fun playGame() {
        var timer: CountDownTimer = object : CountDownTimer(60000, 1500){
            override fun onTick(p0: Long) {
                val timerText = findViewById<TextView>(R.id.time)
                timerText.text=(p0/1000).toString()
                var image = pickImage()
                clickable(image).start()
            }

            override fun onFinish() {
                var intent = Intent(this@TimeActivity, GameEndActivity::class.java)
                intent.putExtra("type", "time")
                intent.putExtra("score", playerScore?.text?.trim())
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
        timer.start()
    }

    private fun clickable(image: ImageButton): CountDownTimer {
        image.setOnClickListener {
            image.setImageResource(R.drawable.hit)
            this.score++
            playerScore?.text = score.toString()
        }
        return object : CountDownTimer(1500, 1500) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                image.setImageResource(R.drawable.hole)
                image.setOnClickListener(null)
            }
        }

    }


    private fun pickImage(): ImageButton {
        val images = findViewById<ConstraintLayout>(R.id.time_mode).touchables
        images.shuffle()
        val randomImage = images[0] as ImageButton
        randomImage.setImageResource(R.drawable.mole)
        return randomImage
    }
}