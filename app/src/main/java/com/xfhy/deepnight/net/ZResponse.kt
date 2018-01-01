package com.xfhy.deepnight.net

/**
 * @author xfhy
 * create at 2018/1/1 12:37
 * description：
 */
interface ZResponse {

    fun <T> onSuccess(dataList: ArrayList<T>)

    fun onError(error: String?)

}