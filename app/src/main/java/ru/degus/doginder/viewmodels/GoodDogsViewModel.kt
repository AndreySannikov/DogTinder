package ru.degus.doginder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.degus.doginder.db.RoomDog
import ru.degus.doginder.repository.RoomDogRepository

class GoodDogsViewModel(private val roomDogRepository: RoomDogRepository) : ViewModel() {

    val dogRoomData: LiveData<List<RoomDog>> = roomDogRepository.getDogs()

    fun deleteDog(dog: RoomDog) {
        roomDogRepository.deleteDog(dog)
    }

}