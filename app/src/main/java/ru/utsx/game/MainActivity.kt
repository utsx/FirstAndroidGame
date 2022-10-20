package ru.utsx.game

import android.app.GameManager
import android.content.Intent
import android.opengl.Matrix
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private var startButton: MaterialButton? = null
    private var nextButton: MaterialButton? = null
    private var inputText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        startButton = findViewById(R.id.start_button)
        nextButton = findViewById(R.id.next_button)
        inputText = findViewById(R.id.edit_text)

        startButton?.setOnClickListener {
            startGame()
        }

        this.nextButton?.setOnClickListener{
            clickNext()
        }
    }

    private fun clickNext(){
        val inputText = findViewById<TextInputEditText>(R.id.edit_text)
        val text = inputText.text.toString()
        val name = text.trim()
        if (name.length !in 1..9){
            val popup = "Введите валидное имя"
            val toast = Toast.makeText(applicationContext, popup, Toast.LENGTH_SHORT)
            toast.show()
        }
        else {
            beginGame(name)
        }
    }

    private fun beginGame(name : String){
        startActivity(Intent(this@MainActivity, ModeActivity::class.java))
    }

    private fun startGame(){
        val startButton = findViewById<MaterialButton>(R.id.start_button)
        val nextButton = findViewById<MaterialButton>(R.id.next_button)
        val inputText = findViewById<TextInputEditText>(R.id.edit_text)
        startButton.visibility = View.INVISIBLE

        val onScreen = arrayOf(nextButton, inputText)

        for(elem in onScreen){
            elem.visibility = View.VISIBLE
        }
    }

}