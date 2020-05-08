package com.example.chucknorris

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.UnstableDefault
import java.util.concurrent.TimeUnit


//import androidx.recyclerView.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var jokeAdapter: JokeAdapter
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var listJokes = ArrayList<Joke>()

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSet()

        my_button.setOnClickListener {
            progress_bar.max = 100
            val currentProgress = 60
            ObjectAnimator.ofInt(progress_bar, "progress", currentProgress)
                .setDuration(1000)
                .start()
            //val loadingImage = (ProgressBar) root.findViewById(R.id.progress_bar)
            //loadingImage.setVisibility(View.VISIBLE)
            findViewById<ProgressBar>(R.id.progress_bar).visibility = VISIBLE
            jokesService()
            //progress_bar.visibility = View.VISIBLE
            findViewById<ProgressBar>(R.id.progress_bar).visibility = INVISIBLE
            jokeAdapter.notifyDataSetChanged()
            //progress_bar.visibility = View.INVISIBLE
            //loadingImage.setVisibility(View.INVISIBLE)
        }

    }

    private fun addDataSet(){
        //val data = DataSource.createDataSet()
        jokeAdapter.submitList(listJokes)
    }

    private fun initRecyclerView() {
        my_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        jokeAdapter = JokeAdapter()
        my_recycler_view.adapter = jokeAdapter
    }

    @UnstableDefault
    private fun jokesService() {
        val jokeService = JokeApiServiceFactory.getJokeApi()
        val singlejoke = jokeService.giveMeAJoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .delay(3000, TimeUnit.MILLISECONDS)
            .subscribeBy(
                onError = { error -> Log.e("Error Joke","$error")  }, //affiche msg d'erreur dans Logcat
                onSuccess = { it -> Log.i("Joke :","$it") //affiche une info dans Logcat listJokes.add(it)
                                listJokes.add(it)}

            )
        compositeDisposable.add(singlejoke)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }





}


