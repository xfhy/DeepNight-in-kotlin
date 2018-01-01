package com.xfhy.deeplibrary.basekit

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author xfhy
 * create at 2018/1/1 10:27
 * description： 所有Activity的父类
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewResId())
        mContext = this

        initIntentData()
        initView()
        initData()
    }

    abstract fun getContentViewResId(): Int
    open fun initIntentData() {}
    abstract fun initView()
    abstract fun initData()

}