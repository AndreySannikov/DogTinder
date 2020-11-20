package ru.degus.doginder.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.degus.doginder.api.ApiFactory
import ru.degus.doginder.db.AppDatabase
import ru.degus.doginder.db.RoomDog
import ru.degus.doginder.db.RoomDogDao

class RoomDogRepository(database: AppDatabase) {
    private val dogDAO: RoomDogDao = database.roomDogDao()

    fun getDogs(): LiveData<List<RoomDog>> {
        return dogDAO.getAll()
    }

    fun insertDog(dog: RoomDog) {
        CoroutineScope(Dispatchers.IO).launch {
            dogDAO.insert(dog)
        }
    }

    fun deleteDog(dog: RoomDog) {
        CoroutineScope(Dispatchers.IO).launch {
            dogDAO.delete(dog)
        }
    }
}