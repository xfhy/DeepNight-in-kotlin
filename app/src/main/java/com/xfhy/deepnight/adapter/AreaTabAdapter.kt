package com.xfhy.deepnight.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author xfhy
 * create at 2018/1/1 14:07
 * description：
 */
class AreaTabAdapter(var fm: FragmentManager, var fragmentList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    private val TAB_TITLES = arrayOf("中国内地", "台湾", "香港", "日本", "韩国", "马来西亚", "泰国", "欧美", "混血")

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = TAB_TITLES.size

    override fun getPageTitle(position: Int): CharSequence = TAB_TITLES[position]
}