package ru.degus.doginder.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class RoomDog(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val breed: String,

    val dogImage: String
)

