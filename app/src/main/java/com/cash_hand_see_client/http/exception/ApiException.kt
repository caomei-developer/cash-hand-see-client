package com.cash_hand_see_client.http.exception

/**
 *创建人:雷富
 *创建时间:2019/6/5 17:05
 *描述:
 */
class ApiException(var code: Int?, var msg: String?) : Exception()