package com.logo.logoquiz.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.logo.logoquiz.model.LogoQuizSet
import com.logo.logoquiz.repo.QuizRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VMLogoQuiz constructor(private val repo: QuizRepo) : ViewModel() {
    private val _lvLogo = MutableLiveData<LogoQuizSet>()
    val lvLogo: LiveData<LogoQuizSet> = _lvLogo

    private val _lvUserScore = MutableLiveData<Int?>()
    val lvUserScore: LiveData<Int?> = _lvUserScore
    var currentPos = 0
    val logosList = MutableLiveData<ArrayList<LogoQuizSet>>()

    fun fetchLogoQuiz() {
        val result = repo.fetchLogos()
        _lvLogo.value = result?.get(currentPos)
        _lvUserScore.value = 0
    }

    /**
     * caculate score and move further
     */
    fun evaluateAndMove(enteredName: String) {
        if (enteredName == lvLogo.value?.name) {
            _lvUserScore.value = lvUserScore.value?.plus(1)
        } else {
            //here we can handle or show error if its in correct
        }
        currentPos += 1
        loadNextLogo()
    }

    /**
     * get next logo and update livedata
     */
    private fun loadNextLogo() {
        logosList.value?.let { list ->
            if (currentPos > 0 && currentPos < list.size) {
                _lvLogo.value = logosList.value?.get(currentPos)
            }
        }
    }
}