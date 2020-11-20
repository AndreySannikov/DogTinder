package ru.degus.doginder.repository

import io.reactivex.Observable
import io.reactivex.Single
import ru.degus.doginder.api.ApiFactory
import ru.degus.doginder.models.Dog

class ApiDogRepository(apiFactory: ApiFactory) {

    private val api = apiFactory

    fun downloadDog(): Single<Dog> {
        return api.getDogApi().getDog()
    }

}