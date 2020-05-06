package com.example.chucknorris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.UnstableDefault


//import androidx.recyclerView.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var jokeAdapter: JokeAdapter

    @UnstableDefault
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        val jokes = JokeList.jokes;
        for(i in jokes) {
            Log.d("blague", i);
        }*/

        initRecyclerView()
        addDataSet()
        jokesService()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        jokeAdapter.submitList(data)
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
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onError = { error -> Log.e("Error Joke","$error")  },
                onSuccess = { it.toString() }
            )
    }





}


