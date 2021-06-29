package vms.android.simplequiz

import androidx.annotation.StringRes

//class Question {
//
//    var questionText: String = ""
//    var answer: Boolean = false
//
//}

data class Question(@StringRes val textResId: Int, val answer: Boolean)