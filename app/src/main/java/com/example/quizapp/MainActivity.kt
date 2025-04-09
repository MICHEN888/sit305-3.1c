package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("QuizApp", MODE_PRIVATE)
        binding.etName.setText(sharedPref.getString("USER_NAME", ""))

        binding.btnStart.setOnClickListener {
            val name = binding.etName.text.toString()
            sharedPref.edit().putString("USER_NAME", name).apply()

            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
            finish()
        }
    }
}
