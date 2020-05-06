package com.example.chucknorris
import io.reactivex.Single
import retrofit2.http.GET

interface JokeApiService {
    @GET("random")
    public fun giveMeAJoke() : Single<Joke>
}