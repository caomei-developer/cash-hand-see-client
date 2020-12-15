package com.cash_hand_see_client.recycler

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class RecyclerViewOnScroll(pullRecyclerView: PullRecyclerView) : RecyclerView.OnScrollListener() {

    var pullRecyclerView: PullRecyclerView? = null

    init {
        this.pullRecyclerView = pullRecyclerView
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        var lastItem = 0
        var firstItem = 0

        var layoutManager = recyclerView.layoutManager
        var totalItemCount = layoutManager!!.itemCount
        when (layoutManager) {
            is GridLayoutManager -> {
                var gridLayoutManager = layoutManager
                firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
                lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition()
                if (lastItem == -1) {
                    lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition()
                }
            }
            is LinearLayoutManager -> {
                var linearLayoutManager = layoutManager
                firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                if (lastItem == -1) {
                    lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                }
            }
            is StaggeredGridLayoutManager -> {
                val staggeredGridLayoutManager = layoutManager
                val lastPositions = IntArray(layoutManager.spanCount)
                staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(lastPositions)
                lastItem = findMax(lastPositions)
                firstItem =
                    staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0]
            }
        }

        if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION) {
            if (pullRecyclerView!!.getPullRefreshEnable())
                pullRecyclerView!!.setSwipeRefreshEnable(true)
        } else {
            pullRecyclerView!!.setSwipeRefreshEnable(false)
        }

        if (pullRecyclerView!!.pushRefreshEnable
            && !pullRecyclerView!!.isRefresh()
            && pullRecyclerView!!.isHasMore()
            && lastItem == totalItemCount - 1
            && !pullRecyclerView!!.LoadMore()
            && (dx > 0 || dy > 0)
        ) {
            pullRecyclerView!!.setIsLoadMore(true)
            pullRecyclerView!!.loadMore()
        }
    }

    fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }

}