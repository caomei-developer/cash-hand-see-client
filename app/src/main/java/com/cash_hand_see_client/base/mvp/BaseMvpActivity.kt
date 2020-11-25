package com.cash_hand_see_client.base.mvp

import androidx.lifecycle.Lifecycle
import autodispose2.AutoDispose
import autodispose2.AutoDisposeConverter
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.cash_hand_see_client.base.BaseActivity

abstract class BaseMvpActivity<T : BasePresenter<BaseView>> : BaseActivity(), BaseView {

    var mPresenter: T? = null

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
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