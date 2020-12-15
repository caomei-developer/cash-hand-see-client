package com.cash_hand_see_client.http.bean

data class Response<out T>(var code: Int, var message: String, val list: T?)

data class Responseed(var code: Int, var message: String)


