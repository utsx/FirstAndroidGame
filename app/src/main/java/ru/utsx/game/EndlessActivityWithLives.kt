package ru.utsx.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

class EndlessActivityWithLives : AppCompatActivity() {

    private var score = 0
    private var lives = 3
    private var speed = 1.0
    private var playerScore: com.google.android.material.textview.MaterialTextView? = null
    private var playerLives: com.google.android.material.textview.MaterialTextView? = null
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra("name").toString()
        setContentView(R.layout.activity_endless_with_lives)

        playerScore = findViewById(R.id.score)
        playerLives = findViewById(R.id.lives)

        playGame()
    }

    private fun playGame() {
        if (this.lives == 0) {
            var intent = Intent(this@EndlessActivityWithLives, GameEndActivity::class.java)
            intent.putExtra("type", "endless")
            intent.putExtra("score", playerScore?.text?.trim())
            intent.putExtra("name", name)
            startActivity(intent)
        } else {
            var image = pickImage()
            clickable(image).start()
        }
    }

    private fun clickable(image: ImageButton): CountDownTimer {
        var check = 0
        image.setOnClickListener {
            image.setImageResource(R.drawable.hit)
            score++
            check = 1
            playerScore?.text = score.toString()
        }
        return object : CountDownTimer((1500 * speed).toLong(), 100) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                speed *= 0.99
                image.setImageResource(R.drawable.hole)
                image.setOnClickListener(null)
                if (check != 1){
                    println(check)
                    lives--
                }
                playerLives?.text = lives.toString()
                playGame()
            }
        }

    }


    private fun pickImage(): ImageButton {
        val images = findViewById<ConstraintLayout>(R.id.endless_mode).touchables
        images.shuffle()
        val randomImage = images[0] as ImageButton
        randomImage.setImageResource(R.drawable.mole)
        return randomImage
    }
}