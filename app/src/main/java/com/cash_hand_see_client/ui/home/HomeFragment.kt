package com.cash_hand_see_client.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cash_hand_see_client.R
import com.cash_hand_see_client.base.mvp.ui.BaseFragment
import com.cash_hand_see_client.ui.home.adapter.BooksComicFragmentAdapter
import com.cash_hand_see_client.ui.home.local.LocalDate
import com.cash_hand_see_client.ui.home.ui.CategoryFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private lateinit var booksComicFragmentAdapter: BooksComicFragmentAdapter
    private var listFragment: MutableList<Fragment> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        booksComicFragmentAdapter = BooksComicFragmentAdapter(childFragmentManager)

        for (local in LocalDate().localList()) {
            tab.addTab(tab.newTab().setText(local.title))
            listFragment.add(CategoryFragment().newInstance(local.type))
        }

        booksComicFragmentAdapter.setDate(LocalDate().localList(), fragments = listFragment)

        view_page.adapter = booksComicFragmentAdapter

        view_page.offscreenPageLimit = LocalDate().localList().size

        tab.setupWithViewPager(view_page)
    }

}