package com.cash_hand_see_client.ui.home.contract

import com.cash_hand_see_client.base.mvp.view.BaseView
import com.cash_hand_see_client.http.bean.Response
import com.cash_hand_see_client.ui.home.bean.BooksComics
import io.reactivex.rxjava3.core.Observable

class CategoryContract {
    interface CategoryModel {
        fun categoryResponse(type: String): Observable<Response<MutableList<BooksComics>>>
    }

    interface View : BaseView {
        override fun showLoading()


        override fun hideLoading()

        override fun onError(code: Int, msg: String)

        fun onSuccess(category: MutableList<BooksComics>)
    }

    interface Presenter {
        fun categoryList(type: String)
    }

}