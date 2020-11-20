package ru.degus.doginder.di

import dagger.Module
import dagger.Provides
import ru.degus.doginder.api.ApiFactory
import ru.degus.doginder.db.AppDatabase
import ru.degus.doginder.repository.ApiDogRepository
import ru.degus.doginder.repository.RoomDogRepository
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun providesRoomDogRepository(appDatabase: AppDatabase): RoomDogRepository {
        return RoomDogRepository(appDatabase)
    }

    @Singleton
    @Provides
    fun providesApiDogRepository(apiFactory: ApiFactory): ApiDogRepository {
        return ApiDogRepository(apiFactory)
    }
}