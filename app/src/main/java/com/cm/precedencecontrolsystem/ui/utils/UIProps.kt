package com.cm.precedencecontrolsystem.ui.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.annotation.LayoutRes

object UIProps {

    fun buildDialog(
        context: Context,
        @LayoutRes layoutResID: Int
    ): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutResID)
        dialog.setCancelable(false)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        dialog.window?.attributes = lp

        return  dialog
    }

    fun isViewEnabled(isEnabled: Boolean, vararg view: View) {
        view.forEach {
            it.isEnabled = isEnabled
        }
    }

    fun isViewEnabled(isEnabled: Boolean, vararg view: Sequence<View>) {
        view.forEach {
            it.forEach {  itemView ->
                val checkBox =  itemView as CheckBox

                if (!checkBox.isChecked)
                    checkBox.isEnabled = isEnabled
            }
        }
    }

    fun setMargin(
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(left, top, right, bottom)
    }
}