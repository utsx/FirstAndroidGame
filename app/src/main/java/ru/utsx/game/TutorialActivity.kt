package ru.utsx.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

class TutorialActivity : AppCompatActivity() {
    private var score = 0
    private var playerScore: com.google.android.material.textview.MaterialTextView? = null
    private var name = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = intent.getStringExtra("name").toString()
        println("Tutorial = $name")

        setContentView(R.layout.activity_tutorial)

        playerScore = findViewById(R.id.score)
        playGame()
    }


    private fun playGame() {
        if (this.score >= 10) {
            val intent = Intent(this@TutorialActivity, GameEndActivity::class.java)
            intent.putExtra("type", "tutorial")
            intent.putExtra("score", playerScore?.text?.trim())
            intent.putExtra("name", name)
            startActivity(intent)
        } else {
            val image = pickImage()
            clickable(image).start()
        }
    }

    private fun clickable(image: ImageButton): CountDownTimer {
        image.setOnClickListener {
            image.setImageResource(R.drawable.hit)
            this.score++
            playerScore?.text = score.toString()
        }
        return object : CountDownTimer(1500, 100) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                image.setImageResource(R.drawable.hole)
                image.setOnClickListener(null)
                playGame()
            }
        }

    }


    private fun pickImage(): ImageButton {
        val images = findViewById<ConstraintLayout>(R.id.tutorial_mode).touchables
        images.shuffle()
        val randomImage = images[0] as ImageButton
        randomImage.setImageResource(R.drawable.mole)
        return randomImage
    }

}