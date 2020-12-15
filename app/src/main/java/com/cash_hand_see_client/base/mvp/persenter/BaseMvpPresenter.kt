package com.cash_hand_see_client.base.mvp.persenter

import com.cash_hand_see_client.base.mvp.view.BaseView

open class BaseMvpPresenter<V : BaseView> : BasePresenter<V>() {

    fun detachView() {
        this.mView = null
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }
}