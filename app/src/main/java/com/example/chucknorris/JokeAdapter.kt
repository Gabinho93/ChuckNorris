package com.example.chucknorris
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.joke_layout.view.*
import kotlin.collections.ArrayList


class JokeAdapter(private val onBottomReached: () -> Unit = {}) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : List<Joke> = ArrayList()
    //ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JokeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeViewHolder -> {
                holder.bind(items[position])
            }

        }
        if(position==items.size-1)
            onBottomReached()
    }


    override fun getItemCount(): Int {
        return items.size
    }
    //fun submitList(jokeList: List<Joke>)

     fun submitList(joke: Joke){
        items = items.plus(joke)
        notifyDataSetChanged()
     }

    class JokeViewHolder constructor(TextView: View): RecyclerView.ViewHolder(TextView){
        private val blague: TextView = TextView.my_joke

        fun bind(joke: Joke){
            //blague.setText(joke.value)
            blague.text = joke.value
        }
    }


}



