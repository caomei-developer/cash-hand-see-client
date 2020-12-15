package com.cash_hand_see_client.http.util

import com.cash_hand_see_client.service.Api
import com.cash_hand_see_client.service.URL

open class NetworkExecutor {
    companion object {
        fun apiConstant(): Api {
            return RetrofitClient.instance!!.retrofit(URL.BASE_URL).create(Api::class.java)
        }
    }
}