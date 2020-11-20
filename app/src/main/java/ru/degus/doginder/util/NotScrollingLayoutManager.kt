package ru.degus.doginder.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager


class NotScrollingLayoutManager(context: Context?) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}