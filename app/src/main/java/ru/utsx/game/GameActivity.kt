package ru.utsx.game

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import ru.utsx.game.games.interfaces.Game
import ru.utsx.game.services.Status
import java.util.stream.Collectors

@Suppress("DEPRECATION")
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if ((intent.getSerializableExtra("game") as Game?)?.play(findViewById<ConstraintLayout>(R.id.game_layout).touchables.stream()
                .collect(Collectors.toList())) == Status.WIN) {
            startActivity(Intent(this@GameActivity, WinActivity::class.java))
        }
    }
}