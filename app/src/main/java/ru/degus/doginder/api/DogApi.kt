package ru.degus.doginder.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import ru.degus.doginder.models.Dog

interface DogApi {
    @GET("random")
    fun getDog(): Single<Dog>
}