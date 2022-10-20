package ru.utsx.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import ru.utsx.game.games.TutorialGame


class ModeActivity : AppCompatActivity() {

    var tutorialGameButton: MaterialButton? = null
    var endLessGameButton: MaterialButton? = null
    var timeGameButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode)
        tutorialGameButton = findViewById(R.id.training_mode)
        endLessGameButton = findViewById(R.id.endless_mode)
        timeGameButton = findViewById(R.id.time_mode)


        tutorialGameButton?.setOnClickListener {
            var myIntent: Intent = Intent(this@ModeActivity, GameActivity::class.java)
            myIntent.putExtra("game", TutorialGame())
            startActivity(myIntent)
        }

        endLessGameButton?.setOnClickListener {
//            var myIntent: Intent = Intent(this@ModeActivity, GameActivity::class.java)
//            myIntent.putExtra("game", EndlessGame())
//            startActivity(myIntent)
        }

        timeGameButton?.setOnClickListener {
//            var myIntent: Intent = Intent(this@ModeActivity, GameActivity::class.java)
//            myIntent.putExtra("game", TimeGame())
//            startActivity(myIntent)
        }
    }
}