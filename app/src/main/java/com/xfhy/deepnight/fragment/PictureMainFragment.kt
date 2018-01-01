package com.xfhy.deepnight.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.xfhy.deeplibrary.basekit.BaseFragment
import com.xfhy.deepnight.R
import com.xfhy.deepnight.adapter.PictureTabAdapter
import kotlinx.android.synthetic.main.fragment_area_main.*

/**
 * @author xfhy
 * create at 2018/1/1 17:14
 * description：
 */
class PictureMainFragment : BaseFragment() {

    companion object {
        fun newInstance(): PictureMainFragment {
            val args = Bundle()

            val fragment = PictureMainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_area_main

    override fun initView() {
        val tabAdapter = PictureTabAdapter(childFragmentManager, getTabData())
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

        val xgFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/xg/")
        fragmentList.add(xgFragment)

        val yy = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/yy/")
        fragmentList.add(yy)

        val yh = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/yh/")
        fragmentList.add(yh)

        val mt = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/mt/")
        fragmentList.add(mt)

        val hs = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/hs/")
        fragmentList.add(hs)

        val qx = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/qx/")
        fragmentList.add(qx)

        val ny = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/ny/")
        fragmentList.add(ny)

        val zyx = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/zyx/")
        fragmentList.add(zyx)

        val qc = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/qc/")
        fragmentList.add(qc)

        val qz = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/qz/")
        fragmentList.add(qz)

        val ly = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/ly/")
        fragmentList.add(ly)

        val yx = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/yx/")
        fragmentList.add(yx)

        val gg = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/gg/")
        fragmentList.add(gg)

        val ns = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/ns/")
        fragmentList.add(ns)

        val zf = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/zf/")
        fragmentList.add(zf)

        val rjxq = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/rjxq/")
        fragmentList.add(rjxq)

        val bjn = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/bjn/")
        fragmentList.add(bjn)

        val mx = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/mx/")
        fragmentList.add(mx)

        val jp = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/jp/")
        fragmentList.add(jp)

        val jxll = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/jxll/")
        fragmentList.add(jxll)

        val btxy = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/btxy/")
        fragmentList.add(btxy)

        val tyjr = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/tyjr/")
        fragmentList.add(tyjr)

        val rg = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/rg/")
        fragmentList.add(rg)

        val bn = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/bn/")
        fragmentList.add(bn)

        val xms = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/xms/")
        fragmentList.add(xms)

        val yw = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/yw/")
        fragmentList.add(yw)

        val zqbb = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/zqbb/")
        fragmentList.add(zqbb)

        val jsby = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/jsby/")
        fragmentList.add(jsby)

        val dcdFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/dcd/")
        fragmentList.add(dcdFragment)

        val ssFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/ss/")
        fragmentList.add(ssFragment)

        val xfFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/xf/")
        fragmentList.add(xfFragment)

        val hs1Fragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/hs1/")
        fragmentList.add(hs1Fragment)

        val npFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/np/")
        fragmentList.add(npFragment)

        val sfFragment = CommonMainFragment.newInstance("http://www.msgao.com/tpfl/sf/")
        fragmentList.add(sfFragment)

        return fragmentList
    }

}