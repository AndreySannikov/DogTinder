package ru.degus.doginder.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import io.reactivex.schedulers.Schedulers
import ru.degus.doginder.App
import ru.degus.doginder.db.RoomDog
import ru.degus.doginder.repository.ApiDogRepository
import ru.degus.doginder.repository.RoomDogRepository
import ru.degus.doginder.util.breed

class MainViewModel(private val roomDogRepository: RoomDogRepository, private val apiDogRepository: ApiDogRepository) : ViewModel() {

    private var list: MutableList<String> = ArrayList()
    val downloadsItemsData: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val errorData: MutableLiveData<Throwable?> by lazy { MutableLiveData<Throwable?>() }

    private fun addNewDog(roomDog: RoomDog) {
        roomDogRepository.insertDog(roomDog)
    }

    fun saveDog() {
        if (list.isNotEmpty()) {
            val dogUrl = list[0]
            val roomDog = RoomDog(
                0,
                dogUrl.breed(),
                dogUrl
            )
            addNewDog(roomDog)
        }
    }

    fun deleteDog() {
        if (list.isNotEmpty()) {
            list.removeAt(0)
            downloadsItemsData.postValue(list)
        }
    }

    fun downloadRandomDog() {
        apiDogRepository.downloadDog()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("LoadDog", "success")
                list.add(it.message)
                downloadsItemsData.postValue(list)
                Glide.with(App.instance)
                    .load(it.message)
                    .preload()
            }, {
                Log.d("LoadDog", "fail")
                errorData.postValue(it)
            })
    }

}