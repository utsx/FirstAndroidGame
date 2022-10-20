package ru.utsx.game.games.interfaces

import android.view.View
import ru.utsx.game.services.Status
import java.io.Serializable

interface Game : Serializable {
    fun play(images: MutableList<View>) : Status
}