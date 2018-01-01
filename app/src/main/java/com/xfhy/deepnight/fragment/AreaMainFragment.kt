package com.xfhy.deepnight.fragment

import com.xfhy.deeplibrary.basekit.BaseFragment
import com.xfhy.deepnight.R
import android.os.Bundle
import android.support.design.widget.TabLayout.MODE_SCROLLABLE
import android.support.v4.app.Fragment
import com.xfhy.deepnight.adapter.AreaTabAdapter
import kotlinx.android.synthetic.main.fragment_area_main.*


/**
 * @author xfhy
 * create at 2018/1/1 14:04
 * description：
 */
class AreaMainFragment : BaseFragment() {

    companion object {
        fun newInstance(): AreaMainFragment {
            val args = Bundle()

            val fragment = AreaMainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_area_main

    override fun initView() {
        val tabAdapter = AreaTabAdapter(childFragmentManager, getTabData())
        vpArea.adapter = tabAdapter
        // 设置默认的缓存个数
        //vpArea.setOffscreenPageLimit(tabAdapter.count)
        tabLayout.setupWithViewPager(vpArea)
        tabLayout.tabMode = MODE_SCROLLABLE
    }


    override fun initData() {

    }

    /**
     * 获取tab数据
     */
    private fun getTabData(): ArrayList<Fragment> {
        val fragmentList = ArrayList<Fragment>()

        val zgndFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/zgnd/")
        fragmentList.add(zgndFragment)

        val twFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/tw/")
        fragmentList.add(twFragment)

        val xgFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/xg/")
        fragmentList.add(xgFragment)

        val rbFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/rb/")
        fragmentList.add(rbFragment)

        val hgFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/hg/")
        fragmentList.add(hgFragment)

        val mlxyFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/mlxy/")
        fragmentList.add(mlxyFragment)

        val tgFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/tg/")
        fragmentList.add(tgFragment)

        val omFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/om/")
        fragmentList.add(omFragment)

        val hxFragment = CommonMainFragment.newInstance("http://www.msgao.com/dqfl/hx/")
        fragmentList.add(hxFragment)


        return fragmentList
    }

}