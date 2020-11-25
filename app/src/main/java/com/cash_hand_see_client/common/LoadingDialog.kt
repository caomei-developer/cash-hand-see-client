package com.cash_hand_see_client.common

import android.app.Dialog
import android.content.Context


class LoadingDialog : Dialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, theme: Int) : super(context, theme)

    class Builder(context: Context) {
        var title: String? = null
        var msg: String? = null
    }
}


fun dialog(title: String, content: String) {

}