package vms.android.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val KEY_INDEX = "index"

    private val questionViewModel: QuestionViewModel by lazy {
        ViewModelProvider(this).get(QuestionViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState called")

        outState.putInt(KEY_INDEX, questionViewModel.currentIndex)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate() called")

        val currentIndex: Int = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        questionViewModel.currentIndex = currentIndex

        updateQuestion()

        yesButton.setOnClickListener {
            checkAnswer(true)
        }

        noButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }

        displayedQuestion.setOnClickListener {
            nextQuestion()
        }

        previousButton.setOnClickListener {
            questionViewModel.moveToPreviousQuestion()
            updateQuestion()
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
        questionViewModel.moveToNextQuestion()
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionViewModel.currentQuestionText
        displayedQuestion.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionViewModel.currentQuestionAnswer

//        if (userAnswer == correctAnswer) {
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
//        }

        var messageResId =  if (userAnswer == correctAnswer) {
                                R.string.correct_toast
                            } else {
                                R.string.incorrect_toast
                            }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

}