package ru.degus.doginder.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.degus.doginder.App
import ru.degus.doginder.R
import ru.degus.doginder.databinding.ItemDogBinding
import ru.degus.doginder.db.RoomDog
import ru.degus.doginder.util.DatabaseDiffCallback

class DatabaseDogsAdapter : RecyclerView.Adapter<DatabaseDogsAdapter.DatabaseDogsViewHolder>() {

    private var items: List<RoomDog> = ArrayList()

    fun setItems(newItems: List<RoomDog>) {
        val diffResult = DiffUtil.calculateDiff(DatabaseDiffCallback(items, newItems), false)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseDogsViewHolder {
        val li = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDogBinding>(li, R.layout.item_dog, parent, false)
        return DatabaseDogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DatabaseDogsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DatabaseDogsViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root){

        lateinit var item : RoomDog

        fun bind(item: RoomDog){
            this.item = item
            binding.tvBreed.text = item.breed
            Glide.with(App.instance)
                .load(item.dogImage)
                .centerCrop()
                .into(binding.ivDog)
        }

    }
}