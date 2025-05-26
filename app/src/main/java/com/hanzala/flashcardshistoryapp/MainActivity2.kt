package com.hanzala.flashcardshistoryapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private var <TextView> TextView.text: String
    get() {
        TODO("Not yet implemented")
    }
    set(value) {}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        class QuizActivity<TextView>(txtQuestion: TextView, txtFeedback: TextView) : AppCompatActivity() {

            private val questions = arrayOf(
                "Nelson Mandela was the president in 1994.",
                "World War I ended in 1920.",
                "The Great Wall of China is visible from the moon.",
                "The Declaration of Independence was signed in 1776.",
                "Julius Caesar was a Roman Emperor."
            )
            private val answers = arrayOf(true, false, false, true, false)

            private var currentIndex = 0
            private var score = 0

            private var txtQuestion: TextView = txtQuestion
            private var txtFeedback: TextView = txtFeedback
            private lateinit var btnTrue: Button
            private lateinit var btnFalse: Button
            private lateinit var btnNext: Button

            @SuppressLint("MissingInflatedId")
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main) // or your correct XML layout


                txtQuestion = findViewById(R.id.txtQuestion)
                txtFeedback = findViewById(R.id.txtFeedback)
                btnTrue = findViewById(R.id.btnTrue)
                btnFalse = findViewById(R.id.btnFalse)
                btnNext = findViewById(R.id.btnNext)

                showQuestion()

                btnTrue.setOnClickListener { checkAnswer(true) }
                btnFalse.setOnClickListener { checkAnswer(false) }

                btnNext.setOnClickListener {
                    currentIndex++
                    if (currentIndex < questions.size) {
                        showQuestion()
                        txtFeedback.text = ""
                    } else {
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra("score", score)
                        intent.putExtra("questions", questions)
                        intent.putExtra("answers", answers)
                        startActivity(intent)
                        finish()
                    }
                }
            }

            inner class ScoreActivity {

            }

            private fun showQuestion() {
                txtQuestion.text = questions[currentIndex]
            }

            private fun checkAnswer(userAnswer: Boolean) {
                if (answers[currentIndex] == userAnswer) {
                    txtFeedback.text = "Correct!"
                    score++
                } else {
                    txtFeedback.text = "Incorrect!"

                }
            }
        }
    }

}
