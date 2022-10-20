package ru.utsx.game.games

import android.annotation.SuppressLint
import android.os.*
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import ru.utsx.game.R
import ru.utsx.game.games.interfaces.Game
import ru.utsx.game.services.Status
import java.io.Serializable
import java.util.stream.Collectors


@Suppress("DEPRECATION", "CAST_NEVER_SUCCEEDS")
class TutorialGame() : Game, Serializable{

    private var score = 0
    private var status: Status = Status.WIN
    private var hitHandler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message): Unit
        {
            val bundle: Bundle = msg.data
            val image: ImageButton = bundle.getBundle("image") as ImageButton
            image.setImageResource(R.drawable.hit)
        }
    }
    private var moleHandler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message): Unit
        {
            val bundle: Bundle = msg.data
            val image: ImageButton = bundle.getBundle("image") as ImageButton
            image.setImageResource(R.drawable.mole)
        }
    }
    private var holeHandler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message): Unit
        {
            val bundle: Bundle = msg.data
            val image: ImageButton = bundle.getBundle("image") as ImageButton
            image.setImageResource(R.drawable.hole)
            image.setOnClickListener(null)
        }
    }

    override fun play(images: MutableList<View>): Status {
        val thread = Thread {
            while (this@TutorialGame.score <= 10) {
                val image = pickImage(images)
                clickable(image)
            }

        }
        thread.start()
        return this.status
    }

    private fun clickable(image: ImageButton): CountDownTimer {
        var msg: Message = this.hitHandler.obtainMessage()
        var bundle = Bundle()
        image.setOnClickListener {
            bundle.putSerializable("image", image as Serializable)
            msg.data = bundle
            this.score += 1
        }
        msg = this.holeHandler.obtainMessage()
        bundle = Bundle()
        return object : CountDownTimer(1000, 100) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                bundle.putSerializable("image", image as Serializable)
                msg.data = bundle
            }
        }
    }

    private fun pickImage(images: MutableList<View>): ImageButton {
        val msg: Message = this.moleHandler.obtainMessage()
        val bundle = Bundle()
        val touchedImages =
            images.stream().map { i -> i as ImageButton }.collect(Collectors.toList())
        touchedImages.shuffle()
        val randomImage = touchedImages[0] as ImageButton
        bundle.putSerializable("image", randomImage as Serializable)
        msg.data = bundle
        return randomImage
    }


}
