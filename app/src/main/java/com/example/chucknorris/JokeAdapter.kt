package com.example.chucknorris
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.joke_layout.view.*
import kotlin.collections.ArrayList


class JokeAdapter(private val onBottomReached: () -> Unit = {}) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : MutableList<Joke> = mutableListOf()
    //private var itemsFav : MutableList<Joke> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val jokeViewCreate = JokeView(parent.context)

        return JokeViewHolder(jokeViewCreate)
        //return JokeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent,false))
        //return JokeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, this, true))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeViewHolder -> {
                //val theJoke = holder.bind(items[position])
                val theJoke = items[position].value
                //val theShareButton: ImageButton
                //val theShareButton: ImageButton = holder.jokeView.findViewById(R.id.share_button)
                val theFavButton: ImageButton = holder.jokeView.findViewById(R.id.fav_button)
                val theIsFav: Boolean = true
                //holder.jokeView.setupView(JokeView.Model(items[position].value,true))

                //val theView: TextView = holder.jokeView.findViewById(R.id.my_joke)


                val theModel = JokeView.Model(theJoke,theFavButton,theIsFav)
                //val theModel = JokeView.Model(theView)

                holder.jokeView.setupView(theModel)
            }

        }
        if(position==items.size-1)
            {onBottomReached()}

    }


    override fun getItemCount(): Int {
        return items.size
    }
    //fun submitList(jokeList: List<Joke>)

     fun submitList(joke: Joke){
        items.add(joke)
        notifyDataSetChanged()
     }

    fun getList(): List<Joke> {
        return items
    }


    fun onJokeRemoved(pos: Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun onItemMoved(pos:Int, dest:Int){
        val fromJoke = items[pos]
        items.remove(fromJoke)
        items.add(dest,fromJoke)
        notifyItemMoved(pos,dest)
    }

    class JokeViewHolder constructor(var jokeView: JokeView): RecyclerView.ViewHolder(jokeView){
        private val blague: TextView = jokeView.my_joke

        fun bind(joke: Joke){
            //blague.setText(joke.value)
            blague.text = joke.value
        }

    }


}



