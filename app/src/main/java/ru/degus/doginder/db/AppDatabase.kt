package ru.degus.doginder.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.degus.doginder.db.RoomDog


@Database(entities = [RoomDog::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDogDao(): RoomDogDao
}