package com.cash_hand_see_client.base.mvp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun needVisibleHint(): Boolean {
        return false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        when (isAdded) {
            true -> {
                val fragments = fragmentManager!!.fragments
                fragments.apply {
                    for (fragment in fragments) {
                        fragment.userVisibleHint = isVisibleToUser
                    }
                }
            }
        }
    }

}