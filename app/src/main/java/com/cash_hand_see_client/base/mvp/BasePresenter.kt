package com.cash_hand_see_client.base.mvp

class BasePresenter<V : BaseView> {
    var mView: V? = null

    fun attachViewe(view: V) {
        mView = view
    }

    fun detachView() {
        this.mView = null
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }

}