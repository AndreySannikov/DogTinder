package ru.degus.doginder.di

import dagger.Module
import dagger.Provides
import ru.degus.doginder.util.RANDOM_DOG_URL
import ru.degus.doginder.api.ApiFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiFactoryModule {
    @Singleton
    @Provides
    fun apiFactory(
        @Named("dog_url") dogUrl: String
    ): ApiFactory {
        return ApiFactory(dogUrl)
    }
    @Provides
    @Named("dog_url")
    fun dogUrl() = RANDOM_DOG_URL
}