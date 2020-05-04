package com.example.chucknorris
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.joke_layout.view.*
import kotlin.collections.ArrayList


class JokeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<Joke> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JokeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

     fun submitList(jokeList: List<Joke>){
         items = jokeList
     }

    class JokeViewHolder constructor(TextView: View): RecyclerView.ViewHolder(TextView){
        val blague = TextView.my_joke

        fun bind(joke: Joke){
            blague.setText(joke.value)
        }
    }


}



