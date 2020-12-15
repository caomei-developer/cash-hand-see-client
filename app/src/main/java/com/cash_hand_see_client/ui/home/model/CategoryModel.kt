package com.cash_hand_see_client.ui.home.model

import com.cash_hand_see_client.http.bean.Response
import com.cash_hand_see_client.http.util.NetworkExecutor
import com.cash_hand_see_client.ui.home.bean.BooksComics
import com.cash_hand_see_client.ui.home.contract.CategoryContract
import io.reactivex.rxjava3.core.Observable

class CategoryModel : CategoryContract.CategoryModel {
    override fun categoryResponse(type: String): Observable<Response<MutableList<BooksComics>>> {
        return NetworkExecutor.apiConstant().homeList(type)
    }
}