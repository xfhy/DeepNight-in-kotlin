package com.xfhy.deepnight.fragment

import android.os.Bundle
import com.xfhy.deeplibrary.basekit.BaseFragment
import com.xfhy.deepnight.R

/**
 * @author xfhy
 * create at 2018/1/1 18:08
 * description：
 */
class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance(): AboutFragment {
            val args = Bundle()

            val fragment = AboutFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_about

    override fun initView() {
    }

    override fun initData() {
    }
}