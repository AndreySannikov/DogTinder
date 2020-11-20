package ru.degus.doginder.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import ru.degus.doginder.App
import ru.degus.doginder.R
import ru.degus.doginder.databinding.FragmentMainBinding
import ru.degus.doginder.di.MainViewModelFactory
import ru.degus.doginder.util.NUMBER_OF_PRELOADS
import ru.degus.doginder.util.NotScrollingLayoutManager
import ru.degus.doginder.view.Layout
import ru.degus.doginder.view.adapters.DownloadDogsAdapter
import ru.degus.doginder.viewmodels.MainViewModel
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: DownloadDogsAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel()

        createRecyclerView()

        observeViewModel()

        preloadDogs()

        btn_good_boys.setOnClickListener {
            navigator.navigateTo(Layout.GOOD_DOGS)
        }
    }

    private fun createRecyclerView() {
        val llm = NotScrollingLayoutManager(navigator as Context)
        llm.orientation = RecyclerView.HORIZONTAL
        binding.rvDownloadDogs.layoutManager = llm

        adapter = DownloadDogsAdapter()
        binding.rvDownloadDogs.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvDownloadDogs)
    }

    private fun createViewModel() {
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)[MainViewModel::class.java]
    }

    override fun injectDagger() {
        App.instance.component.inject(this)
    }

    private fun observeViewModel() {
        mainViewModel.downloadsItemsData.observe(this,
            Observer {
                adapter.setItems(it)
            }
        )

        mainViewModel.errorData.observe(this,
            Observer {
                showInformDialogNonCancelable("Error", it?.message) {
                    navigator.closeApp()
                }
            }
        )
    }

    private fun preloadDogs() {
        with (mainViewModel) {
            for (i in 1..NUMBER_OF_PRELOADS) {
                downloadRandomDog()
            }
        }
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

            val toast = toastCreate()

            if (swipeDir == ItemTouchHelper.LEFT) {
                if (viewHolder is DownloadDogsAdapter.DownloadDogsViewHolder) {
                    Log.d("Swipe", "delete dog")
                    with(mainViewModel) {
                        deleteDog()
                        downloadRandomDog()
                    }
                }
            }
            if (swipeDir == ItemTouchHelper.RIGHT) {
                if (viewHolder is DownloadDogsAdapter.DownloadDogsViewHolder) {
                    toast.show()
                    with(mainViewModel) {
                        saveDog()
                        deleteDog()
                        downloadRandomDog()
                    }
                }
            }
        }
    }

    private fun toastCreate() : Toast {
        val toast = Toast(navigator as Context)
        val view = ImageView(navigator as Context)
        view.setImageResource(R.drawable.toast_good_boy)
        toast.view = view
        toast.setGravity(Gravity.CENTER, 0, 0)
        return toast
    }

}