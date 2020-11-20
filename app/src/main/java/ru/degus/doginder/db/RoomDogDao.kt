package ru.degus.doginder.db
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RoomDogDao {

    @Query("SELECT * FROM dogs")
    fun getAll(): LiveData<List<RoomDog>>

    @Query("SELECT * FROM dogs WHERE id = :id")
    fun getById(id: Long): LiveData<RoomDog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: RoomDog?)

    @Update
    fun update(employee: RoomDog?)

    @Delete
    fun delete(employee: RoomDog?)

}