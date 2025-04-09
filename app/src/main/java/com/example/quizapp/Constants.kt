package com.example.quizapp

object Constants {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(1, "What is the capital of France?", listOf("Paris", "London", "Berlin", "Madrid"), 1),
            Question(2, "Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Jupiter", "Venus"), 2),
            Question(3, "Who wrote 'Hamlet'?", listOf("Shakespeare", "Tolstoy", "Hemingway", "Dickens"), 1)
        )
    }
}
