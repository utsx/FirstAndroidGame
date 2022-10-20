package ru.utsx.game.games

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import ru.utsx.game.R
import ru.utsx.game.games.interfaces.Game
import ru.utsx.game.services.Status
import java.util.stream.Collectors

class TimeGame : Game, java.io.Serializable {
    private var score = 0
    private var buffer = 0


    override fun play(images: MutableList<View>) : Status {
        return Status.WIN
    }

    private fun game(images: MutableList<View>) {
        if (this.score >= 1) {
            buffer++
            return
        }
        else {
            val image = pickImage(images)
            clickable(images, image).start()
        }
    }

    private fun clickable(images: MutableList<View>, image: ImageButton): CountDownTimer {
        image.setOnClickListener {
            image.setImageResource(R.drawable.hit)
            this.score += 1
        }
        return object : CountDownTimer(1000, 100) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                image.setImageResource(R.drawable.hole)
                image.setOnClickListener(null)
                play(images)
            }
        }

    }


    private fun pickImage(images: MutableList<View>): ImageButton {
        val touchedImages =
            images.stream().map { i -> i as ImageButton }.collect(Collectors.toList())
        touchedImages.shuffle()
        val randomImage = touchedImages[0] as ImageButton
        randomImage.setImageResource(R.drawable.mole)
        return randomImage
    }
}