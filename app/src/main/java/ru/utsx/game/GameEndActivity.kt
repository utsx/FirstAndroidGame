package ru.utsx.game

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class GameEndActivity : AppCompatActivity() {

    private var returnToMainMenuButton: MaterialButton? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_end)

        val score: Long = intent.getStringExtra("score").toString().toLong()
        val type: String? = intent.getStringExtra("type")
        val name: String? = intent.getStringExtra("name")

        returnToMainMenuButton = findViewById<MaterialButton>(R.id.return_to_main_menu_button)
        val tutorialText = findViewById<TextView>(R.id.tutorial_end)
        val livesText = findViewById<TextView>(R.id.lives_end)
        val timeText = findViewById<TextView>(R.id.time_expired)
        val bufferText = findViewById<TextView>(R.id.buffer)

        bufferText.text = "$name ваш счет : $score"

        println("type = $type \n name = $name \n score = $score \n" )

        when (type) {
            "endless" -> {
                livesText.visibility = View.VISIBLE
            }
            "time" -> {
                timeText.visibility = View.VISIBLE
            }
            else -> {
                tutorialText.visibility = View.VISIBLE
            }
        }


        returnToMainMenuButton?.setOnClickListener{
            var i : Intent = Intent(this@GameEndActivity, MainMenuActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }

    }
}