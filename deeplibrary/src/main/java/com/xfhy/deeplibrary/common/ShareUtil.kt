package com.xfhy.deeplibrary.common

import android.content.Context
import android.content.Intent
import android.support.annotation.NonNull

/**
 * @author xfhy
 * @create at 2017/11/11 16:07
 * description：分享工具类(本来准备使用ShareSDK集成分享的,
 * 但是需要申请很多的三方app key,可能这个app不能通过审核,于是后来放弃了,使用的是系统的分享方式)
 */
object ShareUtil {

    /**
     *  调用系统分享,分享Text
     */
    fun shareText(@NonNull context: Context, text: String) {
        val intent = Intent()
        intent.type = "text/plain"
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(intent, "分享到"))
    }
}