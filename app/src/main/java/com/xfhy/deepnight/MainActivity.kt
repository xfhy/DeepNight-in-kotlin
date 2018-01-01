package com.xfhy.deepnight

import android.support.v7.app.ActionBarDrawerToggle
import com.xfhy.deeplibrary.basekit.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import com.xfhy.deepnight.fragment.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mAreaFragment: AreaMainFragment? = null
    private var mPictureFragment: PictureMainFragment? = null
    private var mStationFragment: StationMainFragment? = null
    private var mDigFragment: DigMainFragment? = null
    private var mAboutFragment: AboutFragment? = null

    override fun getContentViewResId(): Int = R.layout.activity_main

    override fun initView() {
        setToolBar("首页")
        //导航按钮有旋转特效
        val toggle = ActionBarDrawerToggle(
                this, drawerMain, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close)
        drawerMain.addDrawerListener(toggle)
        toggle.syncState()

        //设置菜单默认选中项
        navigationMain.setCheckedItem(R.id.nav_menu_area)
        navigationMain.setNavigationItemSelectedListener(this)

        showArea()
    }

    /**
     * 测试---------------------------------------------------------
     * 默认将地区显示出来
     */
    private fun showArea() {
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_main_content, AreaMainFragment.newInstance())
        fragmentTransaction.commit()
    }

    override fun initData() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        //这个是HomeAsUp按钮的id永远都是android.R.id.home
            android.R.id.home -> drawerMain.openDrawer(GravityCompat.START)   //将滑动菜单显示出来
        }
        return true
    }

    /**
     * 设置toolbar的标题
     *
     * @param mToolbar Toolbar
     * @param title    标题
     */
    fun setToolBar(title: String) {
        //setSupportActionBar之前设置标题
        toolbar.setTitle(title)
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            //让导航按钮显示出来
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            //设置导航按钮图标
            supportActionBar.setDisplayShowHomeEnabled(true)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        hideAllFragments(fragmentTransaction)
        when (item.itemId) {
            R.id.nav_menu_area -> {
                setToolBar("首页")
                mAreaFragment?.let {
                    fragmentTransaction.show(it)
                } ?: AreaMainFragment.newInstance().let {
                    mAreaFragment = it
                    fragmentTransaction.add(mAreaFragment, "area")
                    fragmentTransaction.show(it)
                }
            }
            R.id.nav_menu_picture -> {
                setToolBar("图片分类")
                if (mPictureFragment == null) {
                    mPictureFragment = PictureMainFragment.newInstance()
                    fragmentTransaction.add(R.id.fl_main_content, mPictureFragment, "picture")
                } else {
                    fragmentTransaction.show(mPictureFragment)
                }
            }
            R.id.nav_menu_station -> {
                setToolBar("名站写真")
                mStationFragment?.let {
                    fragmentTransaction.show(it)
                } ?: StationMainFragment.newInstance().let {
                    mStationFragment = it
                    fragmentTransaction.add(R.id.fl_main_content, mStationFragment, "station")
                    fragmentTransaction.show(it)
                }
            }
            R.id.nav_menu_diagrams -> {
                setToolBar("日本套图")
                mDigFragment?.let {
                    fragmentTransaction.show(it)
                } ?: DigMainFragment.newInstance().let {
                    mDigFragment = it
                    fragmentTransaction.add(R.id.fl_main_content, mDigFragment, "dig")
                    fragmentTransaction.show(it)
                }
            }
            R.id.nav_menu_about -> {
                setToolBar("关于")
                mAboutFragment?.let {
                    fragmentTransaction.show(it)
                } ?: AboutFragment.newInstance().let {
                    mAboutFragment = it
                    fragmentTransaction.add(R.id.fl_main_content, mAboutFragment, "about")
                    fragmentTransaction.show(it)
                }
            }
            else -> {
            }
        }
        fragmentTransaction.commit()
        //这里先测试一下
        //关闭滑动菜单
        drawerMain.closeDrawers()
        return true
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideAllFragments(transaction: FragmentTransaction) {

        //默认当前这个对象作为闭包的it参数   这里避免了mHomeFragment为null时调用transaction.hide()的问题
        mAreaFragment?.let { transaction.hide(it) }
        mPictureFragment?.let { transaction.hide(it) }
        mStationFragment?.let { transaction.hide(it) }
        mDigFragment?.let { transaction.hide(it) }
        mAboutFragment?.let { transaction.hide(it) }
    }

}
