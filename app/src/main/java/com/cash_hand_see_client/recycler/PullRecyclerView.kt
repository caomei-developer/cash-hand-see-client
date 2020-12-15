package com.cash_hand_see_client.recycler

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cash_hand_see_client.R

class PullRecyclerView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var recyclerView: RecyclerView
    var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var pullLoadMoreListener: PullLoadMoreListener

    var hasLoadMore: Boolean = true

    var isLoadMore: Boolean = false

    var isLoadRefresh: Boolean = false

    var loadRefreshEnable: Boolean = true

    var pushRefreshEnable: Boolean = true

    var footerView: View

    var loadMoreText: TextView

    var loadMoreLayout: LinearLayout

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.pull_loadmore_layout, null)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        recyclerView = view.findViewById(R.id.recycler_view)
        footerView = view.findViewById(R.id.footer_view)
        loadMoreLayout = view.findViewById(R.id.load_more_layout)
        loadMoreText = view.findViewById(R.id.load_more_text)
        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                context!!,
                R.color.colorAccent
            )
        )
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayoutOnRefresh())
        recyclerView.isVerticalScrollBarEnabled = true
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addOnScrollListener(RecyclerViewOnScroll(this))
        recyclerView.setOnTouchListener(onTouchRecyclerView())

        footerView.visibility = View.GONE
        addView(view)
    }


    fun setItemAnimator(animator: RecyclerView.ItemAnimator) {
        recyclerView.itemAnimator = animator
    }

    fun addItemDecoration(decor: RecyclerView.ItemDecoration, index: Int) {
        recyclerView.addItemDecoration(decor, index)
    }

    fun addItemDecoration(decor: RecyclerView.ItemDecoration) {
        recyclerView.addItemDecoration(decor)
    }


    fun scrollToTop() {
        recyclerView.scrollToPosition(0)
    }


    fun getPullRefreshEnable(): Boolean {
        return loadRefreshEnable
    }

    fun setSwipeRefreshEnable(enable: Boolean) {
        swipeRefreshLayout.isEnabled = enable
    }

    fun getSwipeRefreshEnable(): Boolean {
        return swipeRefreshLayout.isEnabled
    }

    fun setColorSchemeResources(vararg colorResIds: Int) {
        swipeRefreshLayout.setColorSchemeResources(colorResIds[0])
    }

    inner class onTouchRecyclerView : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return isLoadRefresh || isLoadMore
        }
    }

    fun setRefreshing(isRefreshing: Boolean) {
        swipeRefreshLayout.post(Runnable {
            if (loadRefreshEnable)
                swipeRefreshLayout.isRefreshing = isRefreshing
        })

    }

    interface PullLoadMoreListener {
        fun onRefresh()
        fun onLoadMore()
    }

    fun getFooterViewLayout(): LinearLayout {
        return loadMoreLayout
    }

    fun setFooterViewBackgroundColor(color: Int) {
        loadMoreLayout.setBackgroundColor(ContextCompat.getColor(context, color))
    }

    fun setFooterViewText(text: CharSequence) {
        loadMoreText.text = text
    }

    fun setFooterViewText(resid: Int) {
        loadMoreText.setText(resid)
    }

    fun setFooterViewTextColor(color: Int) {
        loadMoreText.setTextColor(ContextCompat.getColor(context, color))
    }


    fun refresh() {
        if (pullLoadMoreListener != null) {
            pullLoadMoreListener.onRefresh()
        }
    }

    fun loadMore() {
        if (pullLoadMoreListener != null && hasLoadMore) {
            footerView.animate()
                .translationY(0f)
                .setDuration(300)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        footerView.visibility = View.VISIBLE
                    }
                })
                .start()
            invalidate()
            pullLoadMoreListener.onLoadMore()

        }
    }


    fun setPullLoadMoreCompleted() {
        isLoadRefresh = false
        setRefreshing(false)

        isLoadMore = false
        footerView.animate()
            .translationY(footerView.height.toFloat())
            .setDuration(300)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

    }


    fun setOnPullLoadMoreListener(listener: PullLoadMoreListener) {
        pullLoadMoreListener = listener
    }


    fun LoadMore(): Boolean {
        return isLoadMore
    }

    fun setIsLoadMore(isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore
    }

    fun isRefresh(): Boolean {
        return isLoadRefresh
    }

    fun setIsRefresh(isRefresh: Boolean) {
        this.isLoadRefresh = isRefresh
    }

    fun isHasMore(): Boolean {
        return hasLoadMore
    }

    fun setHasMore(hasMore: Boolean) {
        this.hasLoadMore = hasMore
    }

}