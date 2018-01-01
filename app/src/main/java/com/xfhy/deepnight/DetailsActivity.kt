package com.xfhy.deepnight

import android.content.Intent
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import android.view.View
import com.xfhy.basequickadapter.BaseQuickAdapter
import com.xfhy.deeplibrary.basekit.BaseActivity
import com.xfhy.deeplibrary.common.LogUtil
import com.xfhy.deeplibrary.common.dp2px
import com.xfhy.deepnight.adapter.DetailsListAdapter
import com.xfhy.deepnight.adapter.SpacesItemDecoration
import com.xfhy.deepnight.net.NetUtils
import com.xfhy.deepnight.net.ZResponse
import kotlinx.android.synthetic.main.fragment_common_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * @author xfhy
 * create at 2018/1/1 16:22
 * description：
 */
class DetailsActivity : BaseActivity(), BaseQuickAdapter.OnItemClickListener {

    private var mUrl: String = ""
    private var mAdapter: DetailsListAdapter = DetailsListAdapter(R.layout.item_image_details_list, ArrayList())

    override fun getContentViewResId(): Int = R.layout.activity_details

    override fun initIntentData() {
        mUrl = intent.getStringExtra("url")
    }

    override fun initView() {
        setToolBar("详情")
        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvList.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.isFirstOnly(false)
        mAdapter.setDefaultHeight(dp2px(200))
        //设置item之间的间隔
        val decoration = SpacesItemDecoration(16)
        rvList.addItemDecoration(decoration)
        mAdapter.setOnItemClickListener(this)

        //刷新
        srlLayout.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        srlLayout.setOnRefreshListener {
            doNetwork()
        }
    }

    override fun initData() {
        doNetwork()
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

    /**
     * 做网络操作
     */
    private fun doNetwork() {
        NetUtils.reqDetails(mUrl, object : ZResponse {
            override fun <String> onSuccess(dataList: ArrayList<String>) {
                srlLayout.isRefreshing = false
                mAdapter.replaceData(dataList)
            }

            override fun onError(error: String?) {
                LogUtil.e(error)
            }
        })
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val intent = Intent(mContext, ImageDetailsActivity::class.java)
        intent.putExtra("url", adapter?.getItem(position) as String)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}