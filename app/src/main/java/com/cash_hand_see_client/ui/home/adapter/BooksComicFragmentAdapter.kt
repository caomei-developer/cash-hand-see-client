package com.cash_hand_see_client.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.cash_hand_see_client.ui.home.local.Local

class BooksComicFragmentAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private lateinit var localList: List<Local>
    private lateinit var fragments: List<Fragment>

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return localList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return localList[position].title
    }

    fun setDate(localList: List<Local>, fragments: List<Fragment>) {
        this.localList = localList
        this.fragments = fragments
        notifyDataSetChanged()
    }
}