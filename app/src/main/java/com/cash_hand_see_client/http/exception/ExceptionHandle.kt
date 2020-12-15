package com.cash_hand_see_client.http.exception

import android.util.Log
import com.cash_hand_see_client.base.mvp.view.BaseView
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * desc: 异常处理类
 */

class ExceptionHandle {
    companion object {
        var errorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable, mView: BaseView?): String {
            e.printStackTrace()
            if (e is ApiException) {
                errorMsg = e.message.toString()
            } else if (e is HttpException) {
                if (e.code() == 403) {//可以处理一些退出登录逻辑,可以自己修改
                    mView?.onError(e.code(), "请重新登录")
                }
            } else if (e is SocketTimeoutException || e is ConnectException || e is UnknownHostException) { //均视为网络错误
                errorMsg = "网络连接异常"
                mView?.onError(
                    -10,
                    errorMsg
                )
            } else if (e is JsonParseException || e is JSONException || e is ParseException) {   //均视为解析错误
                Log.e("TAG", "数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                mView?.onError(
                    -50,
                    errorMsg
                )
            } else {//未知错误
                Log.e("TAG", "未知错误Debug调试: " + e.message)
                errorMsg = "未知错误，可能抛锚了吧~"
            }
//            Toast.makeText(MyApp.context, errorMsg, Toast.LENGTH_LONG).show()
            mView?.onError(
                -20,
                errorMsg
            )
            return errorMsg
        }

    }


}
