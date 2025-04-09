package com.example.quizapp

data class Question(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)
