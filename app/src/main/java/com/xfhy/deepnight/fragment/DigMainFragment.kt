package com.xfhy.deepnight.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.xfhy.deeplibrary.basekit.BaseFragment
import com.xfhy.deepnight.R
import com.xfhy.deepnight.adapter.DiaTabAdapter
import kotlinx.android.synthetic.main.fragment_area_main.*

/**
 * @author xfhy
 * create at 2018/1/1 17:14
 * description：
 */
class DigMainFragment : BaseFragment() {

    companion object {
        fun newInstance(): DigMainFragment {
            val args = Bundle()

            val fragment = DigMainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_area_main

    override fun initView() {
        val tabAdapter = DiaTabAdapter(childFragmentManager, getTabData())
        vpArea.adapter = tabAdapter
        tabLayout.setupWithViewPager(vpArea)
        //可滑动
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }


    override fun initData() {

    }

    /**
     * 获取tab数据
     */
    private fun getTabData(): ArrayList<Fragment> {
        val fragmentList = ArrayList<Fragment>()

        val rqstar = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/rqstar/")
        fragmentList.add(rqstar)

        val yswebtt = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/yswebtt/")
        fragmentList.add(yswebtt)

        val dgctt = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/dgctt/")
        fragmentList.add(dgctt)

        val bomb = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/bomb.tv/")
        fragmentList.add(bomb)

        val misty = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/misty/")
        fragmentList.add(misty)

        val sabra = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/sabra.net/")
        fragmentList.add(sabra)

        val kstar = CommonMainFragment.newInstance("http://www.msgao.com/rbtt/4kstar/")
        fragmentList.add(kstar)

        return fragmentList
    }

}