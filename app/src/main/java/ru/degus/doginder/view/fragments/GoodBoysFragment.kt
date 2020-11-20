package ru.degus.doginder.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.degus.doginder.App
import ru.degus.doginder.R
import ru.degus.doginder.databinding.FragmentGoodBoysBinding
import ru.degus.doginder.di.GoodDogsViewModelFactory
import ru.degus.doginder.view.Layout
import ru.degus.doginder.view.adapters.DatabaseDogsAdapter
import ru.degus.doginder.viewmodels.GoodDogsViewModel
import javax.inject.Inject


class GoodBoysFragment : BaseFragment<FragmentGoodBoysBinding>(R.layout.fragment_good_boys) {

    private lateinit var goodDogsViewModel: GoodDogsViewModel
    private lateinit var adapter: DatabaseDogsAdapter

    @Inject
    lateinit var goodDogsViewModelFactory: GoodDogsViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel()

        createRecyclerView()

        observeViewModel()
    }

    private fun observeViewModel() {
        goodDogsViewModel.dogRoomData.observe(this,
            Observer {
                if (it.isEmpty()) {
                    navigator.navigateTo(Layout.MAIN)
                } else {
                    adapter.setItems(it.reversed())
                    Log.d("RoomDatabase", "size of database = ${it.size}")
                }
            }
        )
    }

    private fun createRecyclerView() {
        val llm = LinearLayoutManager(navigator as Context)
        llm.orientation = RecyclerView.VERTICAL

        adapter = DatabaseDogsAdapter()
        binding.rvDatabaseDogs.layoutManager = llm
        binding.rvDatabaseDogs.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvDatabaseDogs)
    }

    private fun createViewModel() {
        goodDogsViewModel = ViewModelProviders.of(this, goodDogsViewModelFactory)[GoodDogsViewModel::class.java]
    }

    override fun injectDagger() {
        App.instance.component.inject(this)
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            if (swipeDir == ItemTouchHelper.LEFT) {
                if (viewHolder is DatabaseDogsAdapter.DatabaseDogsViewHolder) {
                    askUser(viewHolder)
                }
            }
            if (swipeDir == ItemTouchHelper.RIGHT) {
                if (viewHolder is DatabaseDogsAdapter.DatabaseDogsViewHolder) {
                    askUser(viewHolder)
                }
            }
        }
    }

    private fun askUser(holder: DatabaseDogsAdapter.DatabaseDogsViewHolder) {
        val toast = Toast(navigator as Context)
        val view = ImageView(navigator as Context)
        view.setImageResource(R.drawable.toast_dog_thx)
        toast.view = view
        showInformDialog(
            getString(R.string.delete),
            getString(R.string.drive_dog_out),
            {
                goodDogsViewModel.deleteDog(holder.item)
            },
            {
                adapter.notifyItemChanged(holder.adapterPosition)
                toast.show()
            }
        )
    }
}