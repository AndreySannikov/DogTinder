package ru.degus.doginder.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import ru.degus.doginder.repository.ApiDogRepository
import ru.degus.doginder.repository.RoomDogRepository
import ru.degus.doginder.viewmodels.GoodDogsViewModel
import ru.degus.doginder.viewmodels.MainViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoodDogsViewModelFactory @Inject constructor(private var roomDogRepository: RoomDogRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GoodDogsViewModel(roomDogRepository) as T
    }
}