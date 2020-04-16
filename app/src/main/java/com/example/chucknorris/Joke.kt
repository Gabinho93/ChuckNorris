package com.example.chucknorris

data class Joke(

    var the_joke: String

) {

    override fun toString(): String {
        return "Joke(joke='$the_joke')"
    }


}