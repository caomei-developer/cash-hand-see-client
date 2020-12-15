package com.cash_hand_see_client.ui.home.presenter

import com.cash_hand_see_client.base.mvp.persenter.BaseMvpPresenter
import com.cash_hand_see_client.base.mvp.view.RxScheduler
import com.cash_hand_see_client.http.exception.ExceptionHandle
import com.cash_hand_see_client.ui.home.contract.CategoryContract
import com.cash_hand_see_client.ui.home.model.CategoryModel

class CategoryPresenter : BaseMvpPresenter<CategoryContract.View>(),
    CategoryContract.Presenter {

    var categoryModel: CategoryContract.CategoryModel? = null

    fun CategoryPresenter() {
        categoryModel = CategoryModel()
    }

    override fun categoryList(type: String) {
        if (!isViewAttached()) {
            return
        }
        categoryModel!!.categoryResponse(type)
            .compose(RxScheduler().observableTransformer())
            .compose(RxScheduler().handleObservableResult())
            .to(mView!!.bindAutoDispose())
            .subscribe({
                run {
                    mView?.onSuccess(it.list!!)
                    mView?.hideLoading()
                }
            }, { t ->
                mView?.hideLoading()
                mView?.let {
                    ExceptionHandle.handleException(t, it)
                }
            })
    }


}