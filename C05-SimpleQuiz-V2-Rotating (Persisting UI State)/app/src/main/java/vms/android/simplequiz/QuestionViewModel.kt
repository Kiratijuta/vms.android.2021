package vms.android.simplequiz

import android.util.Log
import androidx.lifecycle.ViewModel

//private const val TAG = "QuestionViewModel"

class QuestionViewModel: ViewModel() {

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

    var currentIndex = 0

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun moveToNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPreviousQuestion() {
        if (currentIndex == 0) {
            currentIndex = questionBank.size
        }

        currentIndex = (currentIndex - 1) % questionBank.size
    }

//    init {
//        Log.d(TAG, "ViewModel instance created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//
//        Log.d(TAG, "ViewModel instance about to be cleared")
//    }

}