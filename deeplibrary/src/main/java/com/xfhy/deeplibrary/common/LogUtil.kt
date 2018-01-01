package com.xfhy.deeplibrary.common

import android.util.Log


/**
 * @author xfhy
 * create at 2018/1/1 10:50
 * description： 日志打印
 */
object LogUtil {

    const val VERBOSE: Int = 1
    const val DEBUG: Int = 2
    const val INFO: Int = 3
    const val WARN: Int = 4
    const val ERROR: Int = 5
    const val NOTHING: Int = 6
    const val LEVEL: Int = VERBOSE
    const val TAG = "xfhy"

    fun v(msg: String) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG,msg)
        }
    }

    fun d(msg: String) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG,msg)
        }
    }

    fun i(msg: String) {
        if (LEVEL <= INFO) {
            Log.i(TAG,msg)
        }
    }

    fun w(msg: String?) {
        if (LEVEL <= WARN) {
            Log.w(TAG,msg)
        }
    }

    fun e(msg: String?) {
        if (LEVEL <= ERROR) {
            Log.e(TAG,msg)
        }
    }

}