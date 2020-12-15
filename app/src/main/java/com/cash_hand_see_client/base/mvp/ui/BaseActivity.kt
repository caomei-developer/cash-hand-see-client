package com.cash_hand_see_client.base.mvp.ui

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import com.cash_hand_see_client.R
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        when (hasToolbar()) {
            true -> {
                super.setContentView(R.layout.activity_base)
                layoutInflater.inflate(layoutResID, content_view, true)
                close.setOnClickListener {
                    finish()
                }
                try {
                    title_name.text = packageManager.getActivityInfo(this.componentName, 0)
                        .loadLabel(packageManager).toString()
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

            }

            false -> {
                super.setContentView(layoutResID)
            }
        }


        init()

    }

    fun init() {

    }

    fun showDialog() {

    }

    fun hasToolbar(): Boolean {
        return false
    }
}