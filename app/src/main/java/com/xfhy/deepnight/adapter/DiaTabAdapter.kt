package com.xfhy.deepnight.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author xfhy
 * create at 2018/1/1 14:07
 * description：
 */
class DiaTabAdapter(var fm: FragmentManager, var fragmentList: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    private val TAB_TITLES = arrayOf("RQ-STAR", "YS-Web", "DGC", "Bomb#tv", "@misty", "Sabra#net", "4K-STAR")

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = TAB_TITLES.size

    override fun getPageTitle(position: Int): CharSequence = TAB_TITLES[position]
}