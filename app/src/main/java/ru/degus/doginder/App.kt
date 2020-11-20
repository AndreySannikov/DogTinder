package ru.degus.doginder

import android.app.Application
import ru.degus.doginder.di.AppComponent
import ru.degus.doginder.di.DaggerAppComponent
import ru.degus.doginder.di.RoomModule


class App : Application() {

    companion object {
        lateinit var instance: App
    }
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .build()
    }


}