package ru.degus.doginder.util

import androidx.recyclerview.widget.DiffUtil
import ru.degus.doginder.db.RoomDog

class DatabaseDiffCallback(private val oldDogsList: List<RoomDog>, private val newDogsList: List<RoomDog>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDogsList[oldItemPosition].id == newDogsList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldDogsList.size
    }

    override fun getNewListSize(): Int {
        return newDogsList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDogsList[oldItemPosition] == newDogsList[newItemPosition]
    }
}