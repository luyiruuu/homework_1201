package com.example.lab12

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupWindowInsets()
        setupButtonListener()
    }

    private fun setupWindowInsets() {
        findViewById<View>(R.id.main).apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    private fun setupButtonListener() {
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            startService(Intent(this, MyService::class.java))
            Toast.makeText(this, "Open!!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
