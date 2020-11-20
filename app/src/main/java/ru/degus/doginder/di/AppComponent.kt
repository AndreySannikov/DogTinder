package ru.degus.doginder.di

import androidx.fragment.app.FragmentActivity
import dagger.Component
import ru.degus.doginder.view.MainActivity
import ru.degus.doginder.view.fragments.GoodBoysFragment
import ru.degus.doginder.view.fragments.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class, RepoModule::class, ApiFactoryModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(goodBoysFragment: GoodBoysFragment)
}