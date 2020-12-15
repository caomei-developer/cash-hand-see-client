package com.cash_hand_see_client.service

import com.cash_hand_see_client.http.bean.Response
import com.cash_hand_see_client.ui.home.bean.BooksComics
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //home  list response
    @GET("?")
    fun homeList(@Query("mhlb") mhlb: String): Observable<Response<MutableList<BooksComics>>>


}