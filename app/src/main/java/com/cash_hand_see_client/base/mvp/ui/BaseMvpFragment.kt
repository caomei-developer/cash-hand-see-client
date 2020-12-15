package com.cash_hand_see_client.base.mvp.ui

import androidx.lifecycle.Lifecycle
import autodispose2.AutoDispose
import autodispose2.AutoDisposeConverter
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.cash_hand_see_client.base.mvp.persenter.BaseMvpPresenter
import com.cash_hand_see_client.base.mvp.view.BaseView

abstract class BaseMvpFragment<V : BaseView, T : BaseMvpPresenter<V>> : BaseFragment(),
    BaseView {

    var mPresenter: T? = null


    override fun onDestroyView() {
        super.onDestroyView()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        super.onDestroyView()
    }

    override fun <T> bindAutoDispose(): AutoDisposeConverter<T> {
        return AutoDispose.autoDisposable(
            AndroidLifecycleScopeProvider.from(
                this,
                Lifecycle.Event.ON_DESTROY
            )
        )
    }
}