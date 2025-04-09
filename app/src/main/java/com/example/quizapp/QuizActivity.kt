package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questionList: List<Question>
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("USER_NAME")
        questionList = Constants.getQuestions()

        setQuestion()

        binding.btnSubmit.setOnClickListener {
            val selectedOptionId = binding.rgOptions.checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val answerIndex = binding.rgOptions.indexOfChild(selectedRadioButton) + 1
                val correctAnswer = questionList[currentQuestionIndex].correctAnswer

                if (answerIndex == correctAnswer) {
                    selectedRadioButton.setBackgroundColor(Color.GREEN)
                    score++
                } else {
                    selectedRadioButton.setBackgroundColor(Color.RED)
                    val correctRadioButton = binding.rgOptions.getChildAt(correctAnswer - 1) as RadioButton
                    correctRadioButton.setBackgroundColor(Color.GREEN)
                }

                binding.btnSubmit.isEnabled = false

                binding.btnSubmit.postDelayed({
                    currentQuestionIndex++
                    if (currentQuestionIndex < questionList.size) {
                        setQuestion()
                    } else {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("USER_NAME", userName)
                        intent.putExtra("SCORE", score)
                        intent.putExtra("TOTAL", questionList.size)
                        startActivity(intent)
                        finish()
                    }
                }, 1000)
            }
        }
    }

    private fun setQuestion() {
        val question = questionList[currentQuestionIndex]

        binding.tvQuestion.text = question.question
        binding.rgOptions.clearCheck()

        for (i in 0 until binding.rgOptions.childCount) {
            val radioButton = binding.rgOptions.getChildAt(i) as RadioButton
            radioButton.text = question.options[i]
            radioButton.setBackgroundColor(Color.TRANSPARENT)
        }

        binding.progressBar.progress = ((currentQuestionIndex + 1) * 100) / questionList.size
        binding.btnSubmit.isEnabled = true
    }
}
