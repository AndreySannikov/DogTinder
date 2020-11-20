package ru.degus.doginder.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import ru.degus.doginder.R
import ru.degus.doginder.view.MainActivity
import ru.degus.doginder.view.MainNavigator

abstract class BaseFragment<B : ViewDataBinding>(private val layoutId: Int) : Fragment() {

    protected lateinit var navigator: MainNavigator
    protected lateinit var binding: B

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as MainActivity
    }

    abstract fun injectDagger()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        injectDagger()

        binding = DataBindingUtil.inflate(
            inflater, layoutId, container, false
        )

        return binding.root
    }

    fun showInformDialog(title: String, message: String?, callYes: (() -> Unit)? = null, callNo: (() -> Unit)? = null) {
        AlertDialog.Builder(navigator as Context)
            .setTitle(title)
            .setMessage(message ?: getString(R.string.no_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                callYes?.invoke()
            }
            .setNegativeButton(getString(R.string.no)) { _, _ ->
                callNo?.invoke()
            }
            .create()
            .show()
    }

    fun showInformDialogNonCancelable(title: String, message: String?, call: (() -> Unit)? = null) {
        AlertDialog.Builder(navigator as Context)
            .setTitle(title)
            .setMessage(message ?: getString(R.string.no_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                call?.invoke()
            }
            .create()
            .show()
    }
}