package com.logo.logoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.logo.logoquiz.base.BaseViewModelFactory
import com.logo.logoquiz.repo.QuizRepo
import com.logo.logoquiz.viewModel.VMLogoQuiz

/**
 * Code can be improvised by making use of dependecy injection and binding concept
 * due to time contraint makind it simple
 * trying to implement testcase as well VMLogoQuizTestCase
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel =
            ViewModelProvider(this, BaseViewModelFactory(QuizRepo())).get(VMLogoQuiz::class.java)
        viewModel.fetchLogoQuiz()

        handleObservers(viewModel)
        handleListener(viewModel)
    }

    /**
     * method will handle button click
     */
    private fun handleListener(viewModel: VMLogoQuiz) {
        findViewById<TextView>(R.id.btnNext).setOnClickListener {
            val text = findViewById<TextView>(R.id.etLogoName).text
            if (!text.isNullOrEmpty()) {
                viewModel.evaluateAndMove(text.toString())
            }
        }
    }

    /*
    method will observe livedata and display it on ui
     */
    private fun handleObservers(viewModel: VMLogoQuiz) {
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        viewModel.lvLogo.observe(this, Observer {
            Glide.with(this)
                .load(it.imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivLogo);
        })

        viewModel.lvUserScore.observe(this, Observer {
            findViewById<TextView>(R.id.tvUserScore).text = it.toString()
        })
    }
}