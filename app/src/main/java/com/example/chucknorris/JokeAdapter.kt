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

    var items : MutableList<Joke> = mutableListOf()
    var itemsFav : MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val jokeViewCreate = JokeView(parent.context)

        return JokeViewHolder(jokeViewCreate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is JokeViewHolder -> {
                val theJoke = items[position].value
                //val theJoke = holder.jokeView.my_joke
                val theShareButton: ImageButton = holder.jokeView.findViewById(R.id.share_button)
                val theFavButton: ImageButton = holder.jokeView.findViewById(R.id.fav_button)
                val theIsFav: Boolean = false

                if(theIsFav){ //La boucle qui va déterminer si l'items est déjà "fav" ou non puis attribuer l'icône étoile pleine ou non
                    theFavButton.setImageResource(R.drawable.ic_star_black_24dp)
                }

                theFavButton.setOnClickListener {

                    if(itemsFav.contains(items[position].id)){
                        itemsFav.remove(items[position].id)
                        theFavButton.setImageResource(R.drawable.ic_star_border_black_24dp)
                    }

                    else{
                        itemsFav.add(items[position].id)
                        theFavButton.setImageResource(R.drawable.ic_star_black_24dp)
                    }
                }

                val theModel = JokeView.Model(theJoke,theShareButton,theFavButton,theIsFav)

                holder.jokeView.setupView(theModel)
            }

        }
        if(position==items.size-1)
            {onBottomReached()}

    }


    override fun getItemCount(): Int {
        return items.size
    }

     fun submitList(joke: Joke){
        items.add(joke)
        notifyDataSetChanged()
     }

    fun submitFav(joke:String){
        itemsFav.add(joke)
        notifyDataSetChanged()
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
            blague.text = joke.value
        }

    }


}



