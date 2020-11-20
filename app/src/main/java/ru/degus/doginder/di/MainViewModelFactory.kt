package ru.degus.doginder.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import ru.degus.doginder.repository.ApiDogRepository
import ru.degus.doginder.repository.RoomDogRepository
import ru.degus.doginder.viewmodels.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(private var roomDogRepository: RoomDogRepository,
                                                   private var apiDogRepository: ApiDogRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(roomDogRepository, apiDogRepository) as T
    }
}