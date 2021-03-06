package com.example.chucknorris
import com.jakewharton.retrofit2.converter.kotlinx.serialization.*
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object JokeApiServiceFactory{

    @UnstableDefault
    public fun getJokeApi() : JokeApiService{
            val builder = Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return builder.create(JokeApiService::class.java)
    }
}