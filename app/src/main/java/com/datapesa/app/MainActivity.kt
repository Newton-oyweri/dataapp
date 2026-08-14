package com.datapesa.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.helloButton)
        val statusText = findViewById<TextView>(R.id.statusText)

        button.setOnClickListener {
            statusText.setText(R.string.clicked_text)
        }
    }
}