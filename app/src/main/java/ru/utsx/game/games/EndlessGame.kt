package ru.utsx.game.games

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import ru.utsx.game.R
import ru.utsx.game.games.interfaces.Game
import ru.utsx.game.services.Status
import java.util.stream.Collectors

class EndlessGame: Game, java.io.Serializable{

    private var score = 0
    private var lives = 3
    private var speed = 1

    override fun play(images: MutableList<View>): Status {
        return Status.WIN
    }

    private fun clickable(images: MutableList<View>, image: ImageButton): CountDownTimer {
        var check = 0
        image.setOnClickListener {
            image.setImageResource(R.drawable.hit)
            score++
            check = 1
        }
        if (check == 0){
            lives--
        }
        return object : CountDownTimer((1500 * speed).toLong(), 100) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                image.setImageResource(R.drawable.hole)
                image.setOnClickListener(null)
                speed = (speed - (speed * 0.01)).toInt()
                print(speed)
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