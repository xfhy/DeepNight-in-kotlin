package com.xfhy.deeplibrary.basekit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author xfhy
 * create at 2018/1/1 10:34
 * description：父类fragment
 */
abstract class BaseFragment : Fragment() {

    protected var mView: View? = null
    protected var mContext: Context? = null
    protected var isInit: Boolean = false
    protected var isLoad: Boolean = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater?.inflate(getLayoutResId(), container, false)
        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isInit = true
        initArguments()
        isCanLoadData()
        initView()
        initData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isCanLoadData()
    }

    protected open fun initArguments() {}

    fun isCanLoadData() {
        if (!isInit) {
            return
        }

        if (userVisibleHint && !isLoad) {
            lazyLoad()
        } else {
            if (isLoad) {
                stopLoad()
            }
        }
    }

    /**
     * 当视图已经对用户不可见并且加载过数据,如果需要在切换到其他页面时停止加载,则可以重写该方法
     */
    protected open fun stopLoad() {}

    protected open fun lazyLoad() {
        isLoad = true
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()
}