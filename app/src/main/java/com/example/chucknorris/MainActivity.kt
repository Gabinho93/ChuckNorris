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
    //private var listJokes = ArrayList<Joke>()
    //private var listJokes = Joke(emptyList(),"","","","","","")
    private val n:Long = 10

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        //addDataSet()

        my_button.setOnClickListener {
            //val loadingImage = (ProgressBar) root.findViewById(R.id.progress_bar)
            //loadingImage.setVisibility(View.VISIBLE)
            //findViewById<ProgressBar>(R.id.progress_bar).visibility = VISIBLE
            jokesService() //10
            //progress_bar.visibility = View.VISIBLE
            //findViewById<ProgressBar>(R.id.progress_bar).visibility = INVISIBLE

            //progress_bar.visibility = View.INVISIBLE
            //loadingImage.setVisibility(View.INVISIBLE)
        }



    }

    //private fun addDataSet(){
        //val data = DataSource.createDataSet()
        //jokeAdapter.submitList(listJokes)
    //}

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
            .repeat(n)
            .doOnSubscribe{
                progress_bar.max = 100
                val currentProgress = 100
                ObjectAnimator.ofInt(progress_bar, "progress", currentProgress)
                    .setDuration(2000)
                    .start()
                progress_bar.visibility = View.VISIBLE
            }
            .doAfterTerminate {
                progress_bar.visibility = View.INVISIBLE
            }
            //.delay(3000, TimeUnit.MILLISECONDS)
            .subscribeBy(
                onError = { error -> Log.e("Error Joke","$error")  }, //affiche msg d'erreur dans Logcat
                onNext = { it -> Log.i("Joke :","$it") //affiche une info dans Logcat listJokes.add(it)
                        //jokeAdapter.add(it)
                        jokeAdapter.submitList(it) },
                onComplete = {
                    jokeAdapter.notifyDataSetChanged()
                }

            )
        compositeDisposable.add(singlejoke)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }





}



