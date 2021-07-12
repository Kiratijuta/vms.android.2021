package vms.android.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(  Question(R.string.question_1, true),
                                        Question(R.string.question_2, true),
                                        Question(R.string.question_3, false),
                                        Question(R.string.question_4, false),
                                        Question(R.string.question_5, false),
                                        Question(R.string.question_6, true),
                                        Question(R.string.question_7, false),
                                        Question(R.string.question_8, false),
                                        Question(R.string.question_9, true),
                                        Question(R.string.question_10, false))
    private var currentIndex = 0

    private val currentQuestion: Question
                get() = questionBank[currentIndex]

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate() called")

        updateQuestion()

        yesButton.setOnClickListener {
            answerQuestion(true)
        }

        noButton.setOnClickListener {
            answerQuestion(false)
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }

        displayedQuestion.setOnClickListener {
            nextQuestion()
        }

        previousButton.setOnClickListener {
            previousQuestion()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy() called")
    }


    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size

        updateQuestion()
    }

    private fun previousQuestion() {
        if (currentIndex == 0) {
            currentIndex = questionBank.size
        }
        currentIndex = (currentIndex - 1) % questionBank.size

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = currentQuestion.textResId
        displayedQuestion.setText(questionTextResId)

        updateButtonVisibility(currentQuestion.userAnswer == null)
    }

    private fun answerQuestion(userAnswer: Boolean) {
        currentQuestion.userAnswer = userAnswer

        updateButtonVisibility(false)

        if (isReadyToGrade()) {
            val userScore = questionBank.filter { it.userAnswer == it.answer }.count()
            val totalScore = (userScore * 100.0) / questionBank.count()

            Toast.makeText(this, "You score is $totalScore%", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateButtonVisibility(state: Boolean) {
        yesButton.isVisible = state
        noButton.isVisible = state
    }

    private fun isReadyToGrade(): Boolean {
        return questionBank.filter { it.userAnswer != null }.count() == questionBank.count()
    }

}