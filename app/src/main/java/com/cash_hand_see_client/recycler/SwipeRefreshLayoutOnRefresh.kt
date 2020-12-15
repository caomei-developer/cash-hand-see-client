package com.cash_hand_see_client.recycler

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class SwipeRefreshLayoutOnRefresh : SwipeRefreshLayout.OnRefreshListener {
    lateinit var pullRecyclerView: PullRecyclerView

    fun SwipeRefreshLayoutOnRefresh(pullRecyclerView: PullRecyclerView) {
        this.pullRecyclerView = pullRecyclerView
    }

    override fun onRefresh() {
        if (!pullRecyclerView.isLoadRefresh) {
            pullRecyclerView.isLoadRefresh = true
            pullRecyclerView.refresh()
        }

    }
}