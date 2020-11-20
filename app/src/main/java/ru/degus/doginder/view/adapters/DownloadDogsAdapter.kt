package ru.degus.doginder.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.degus.doginder.App
import ru.degus.doginder.R
import ru.degus.doginder.databinding.ItemDogBinding
import ru.degus.doginder.util.breed

class DownloadDogsAdapter : RecyclerView.Adapter<DownloadDogsAdapter.DownloadDogsViewHolder>() {

    private var items: List<String> = ArrayList()

    fun setItems(newItems : List<String>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadDogsViewHolder {
        val li = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDogBinding>(li, R.layout.item_dog, parent, false)
        return DownloadDogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DownloadDogsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DownloadDogsViewHolder(private val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            binding.tvBreed.text = item.breed()

            Glide.with(App.instance)
                .load(item)
                .centerCrop()
                .placeholder(R.color.colorPrimaryDark)
                .into(binding.ivDog)
        }
    }
}