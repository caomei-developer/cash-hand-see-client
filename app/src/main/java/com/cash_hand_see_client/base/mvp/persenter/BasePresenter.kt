package com.cash_hand_see_client.base.mvp.persenter

import com.cash_hand_see_client.base.mvp.view.BaseView

open class BasePresenter<V : BaseView> {
    var mView: V? = null
    fun attachView(view: V) {
        this.mView = view
    }

}