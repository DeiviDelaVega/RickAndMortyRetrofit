package com.example.rickandmortyretrofit.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener : RecyclerView.OnScrollListener() {
    private var previousTotalItems = 0
    private var loading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (loading && totalItemCount > previousTotalItems) {
            loading = false
            previousTotalItems = totalItemCount
        }

        val visibleThresholds = 5
        if (!loading && (totalItemCount - visibleItemCount <= firstVisibleItemPosition + visibleThresholds)) {
            onLoadMore()
            loading = true
        }



    }

    abstract fun onLoadMore()

}