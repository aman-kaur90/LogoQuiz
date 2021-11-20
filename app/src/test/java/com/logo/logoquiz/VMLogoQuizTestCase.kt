package com.logo.logoquiz

import androidx.lifecycle.Observer
import com.logo.logoquiz.repo.QuizRepo
import com.logo.logoquiz.viewModel.VMLogoQuiz
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class VMLogoQuizTestCase {

@Mock
lateinit var repo: QuizRepo

lateinit var viewModel: VMLogoQuiz

@Mock
lateinit var loadingScoreObserver: Observer<Int?>

@Before
fun setUp() {
    MockitoAnnotations.initMocks(this)
    viewModel = VMLogoQuiz(repo)
}

@Test
fun evaluate_and_move() {
    viewModel.lvUserScore.observeForever(loadingScoreObserver)
    Thread.sleep(1000)
    Mockito.verify(loadingScoreObserver).onChanged(0)
}
}