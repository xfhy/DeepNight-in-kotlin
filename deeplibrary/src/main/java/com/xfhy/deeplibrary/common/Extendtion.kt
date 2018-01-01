package com.xfhy.deeplibrary.common

import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast

/**
 * @author xfhy
 * create at 2018/1/1 10:46
 * description： 扩展
 */

/**
 * 弹出toast
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Fragment.dp2px(dpValue: Int): Int {
    val scale = context.getResources().getDisplayMetrics().density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.dp2px(dpValue: Int): Int {
    val scale = resources.getDisplayMetrics().density
    return (dpValue * scale + 0.5f).toInt()
}
