package ru.utsx.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class ModeActivity : AppCompatActivity() {

    var tutorialGameButton: MaterialButton? = null
    var endLessGameButton: MaterialButton? = null
    var timeGameButton: MaterialButton? = null
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra("name").toString()
        setContentView(R.layout.activity_mode)
        tutorialGameButton = findViewById(R.id.training_mode)
        endLessGameButton = findViewById(R.id.endless_mode)
        timeGameButton = findViewById(R.id.time_mode_button)


        tutorialGameButton?.setOnClickListener {
            var i = Intent(this@ModeActivity, TutorialActivity::class.java)
            i.putExtra("name", name)
            startActivity(i)
        }

        endLessGameButton?.setOnClickListener {
            var i = Intent(this@ModeActivity, EndlessActivityWithLives::class.java)
            i.putExtra("name", name)
            startActivity(i)

        }

        timeGameButton?.setOnClickListener {
            var i = Intent(this@ModeActivity, TimeActivity::class.java)
            i.putExtra("name", name)
            startActivity(i)
        }
    }
}