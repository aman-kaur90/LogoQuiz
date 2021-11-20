package com.logo.logoquiz.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.logo.logoquiz.repo.QuizRepo
import com.logo.logoquiz.viewModel.VMLogoQuiz

class BaseViewModelFactory constructor(private val repository: QuizRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(VMLogoQuiz::class.java)) {
            VMLogoQuiz(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}