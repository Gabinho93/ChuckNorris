package com.example.chucknorris
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.joke_layout.view.*
import kotlin.collections.ArrayList


class JokeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<Joke> = ArrayList()
    //ArrayList()
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
    //fun submitList(jokeList: List<Joke>)

     fun submitList(joke: Joke){
        items = items.plus(joke)
     }

    class JokeViewHolder constructor(TextView: View): RecyclerView.ViewHolder(TextView){
        private val blague: TextView = TextView.my_joke

        fun bind(joke: Joke){
            //blague.setText(joke.value)
            blague.text = joke.value
        }
    }


}



