package ru.degus.doginder.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.degus.doginder.App
import ru.degus.doginder.db.AppDatabase
import ru.degus.doginder.db.RoomDog
import javax.inject.Singleton

@Module
class RoomModule(application: App) {

    private var app = application

    private val databaseCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d("RoomDatabaseModule", "onCreate")
        }
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .addCallback(databaseCallback)
            .build()
    }

    @Singleton
    @Provides
    fun providesDogDAO(appDatabase: AppDatabase) = appDatabase.roomDogDao()

}