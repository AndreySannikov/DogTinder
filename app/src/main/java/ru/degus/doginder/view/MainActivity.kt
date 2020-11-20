package ru.degus.doginder.view


import android.os.Bundle
import android.renderscript.ScriptGroup
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import ru.degus.doginder.App.Companion.instance
import ru.degus.doginder.R

import ru.degus.doginder.di.MainViewModelFactory
import ru.degus.doginder.viewmodels.MainViewModel
import javax.inject.Inject

enum class Layout(val id: Int) {
    MAIN(R.id.mainFragment),
    GOOD_DOGS(R.id.goodBoysFragment)
}

interface MainNavigator {
    fun navigateTo(layout: Layout)
    fun closeApp()
    fun toast(msg: String)
}

class MainActivity : FragmentActivity(), MainNavigator {

    private lateinit var nav: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav = Navigation.findNavController(this, R.id.my_nav_host_fragment)
    }

    override fun navigateTo(layout: Layout) {
        Log.d("MainActivity", "onNextFragment = $layout")
        nav.navigate(layout.id)
    }

    override fun closeApp() {
        finish()
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }




}