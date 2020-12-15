package com.cash_hand_see_client.base.mvp.view

import autodispose2.AutoDisposeConverter

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(error: Int, msg: String)

    fun <T> bindAutoDispose(): AutoDisposeConverter<T>

}