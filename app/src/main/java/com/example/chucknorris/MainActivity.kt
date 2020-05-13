package com.example.chucknorris

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.joke_layout.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


//import androidx.recyclerView.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var jokeAdapter: JokeAdapter
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    //private var listJokes = ArrayList<Joke>()
    //private var listJokes = Joke(emptyList(),"","","","","","")
    //ViewModelProviders.of(this)
    private val n:Long = 10
    private val JOKES_KEY = "jokes_key"
    private val JOKES_FAV = "jokes_fav"


    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        val sharedPreferences: SharedPreferences = getSharedPreferences(JOKES_FAV,MODE_PRIVATE)
        if (savedInstanceState != null){
            //savedInstanceState.getString(JOKES_KEY)?.let {
             //   Json(JsonConfiguration.Stable).parse(Joke.serializer().list, it)

            val jokesSaved = savedInstanceState.getString(JOKES_KEY)?.let { Json(JsonConfiguration.Stable).parse(Joke.serializer().list, it) }
            jokesSaved?.forEach { jokeAdapter.submitList(it) }

            //val favJokes = savedInstanceState.getString(JOKES_FAV)?.let { Json(JsonConfiguration.Stable).parse(String.serializer().list, it) }
            //favJokes?.forEach{ jokeAdapter.submitFav(it) }

        }

        else {
            jokesService()
        }



        Swipe.setColorSchemeColors(Color.GRAY)
        Swipe.setOnRefreshListener {
                jokesService()
                Swipe.isRefreshing
        }


    }

    /*private fun saveData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("Favorites jokes",MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val json = Json(JsonConfiguration.Stable).stringify(Joke.serializer().list,jokeAdapter.getList())
        editor.putString(JOKES_KEY,json)
        editor.apply()
    } */


    @UnstableDefault
    private fun initRecyclerView() {
        my_recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        jokeAdapter = JokeAdapter{jokesService()}
        my_recycler_view.adapter = jokeAdapter

        val itemTouch = JokeTouchHelper(
            { src -> jokeAdapter.onJokeRemoved(src) },
            { src, dest -> jokeAdapter.onItemMoved(src, dest)}
        )

        itemTouch.attachToRecyclerView(my_recycler_view)
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
                Swipe.stopNestedScroll()
            }
            .doAfterTerminate {
                progress_bar.visibility = View.INVISIBLE
            }
            .subscribeBy(
                onError = { error -> Log.e("Error Joke","$error")  }, //affiche msg d'erreur dans Logcat
                onNext = { it -> Log.i("Joke :","$it") //affiche une info dans Logcat listJokes.add(it)
                        //
                        jokeAdapter.submitList(it) },
                onComplete = {
                }

            )
        compositeDisposable.add(singlejoke)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val json = Json(JsonConfiguration.Stable).stringify(Joke.serializer().list,jokeAdapter.items)
        outState.putString(JOKES_KEY, json)

        val jsonFav=Json(JsonConfiguration.Stable).stringify(String.serializer().list,jokeAdapter.itemsFav)
        outState.putString(JOKES_FAV,jsonFav)

        super.onSaveInstanceState(outState)
    }







}



