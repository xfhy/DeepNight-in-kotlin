package com.xfhy.deepnight.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.xfhy.basequickadapter.BaseQuickAdapter
import com.xfhy.deeplibrary.basekit.BaseFragment
import com.xfhy.deeplibrary.common.LogUtil
import com.xfhy.deeplibrary.common.dp2px
import com.xfhy.deeplibrary.common.showToast
import com.xfhy.deepnight.DetailsActivity
import com.xfhy.deepnight.R
import com.xfhy.deepnight.adapter.HomeListAdapter
import com.xfhy.deepnight.adapter.SpacesItemDecoration
import com.xfhy.deepnight.bean.DivListImgBean
import com.xfhy.deepnight.net.NetUtils
import com.xfhy.deepnight.net.ZResponse
import kotlinx.android.synthetic.main.fragment_common_main.*

/**
 * @author xfhy
 * create at 2018/1/1 14:16
 * description：首页  通用
 */
class CommonMainFragment : BaseFragment(), BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private var mUrl: String = ""
    private var mPageCount = 1
    private var mAdapter: HomeListAdapter = HomeListAdapter(R.layout.item_common_main, ArrayList())

    companion object {
        fun newInstance(url: String): CommonMainFragment {
            val args = Bundle()
            args.putString("url", url)

            val fragment = CommonMainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_common_main

    override fun initArguments() {
        mUrl = arguments.getString("url")
    }

    override fun initView() {
        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvList.adapter = mAdapter
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.isFirstOnly(false)
        mAdapter.setDefaultHeight(dp2px(300))
        mAdapter.setOnLoadMoreListener(this, rvList)
        //设置item之间的间隔
        val decoration = SpacesItemDecoration(16)
        rvList.addItemDecoration(decoration)
        mAdapter.setOnItemClickListener(this)

        //刷新
        srlLayout.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        srlLayout.setOnRefreshListener {
            doNetwork(false)
        }
    }

    override fun initData() {
    }

    override fun lazyLoad() {
        super.lazyLoad()
        doNetwork(false)
    }

    /**
     * 做网络操作
     */
    fun doNetwork(isLoadMore: Boolean) {
        if (isLoadMore) {
            mPageCount++
        }
        NetUtils.reqOutList(mUrl, mPageCount, object : ZResponse {
            override fun <DivListImgBean> onSuccess(dataList: ArrayList<DivListImgBean>) {
                if (isLoadMore) {
                    LogUtil.e("加载更多成功")
                    mAdapter.addData(dataList)
                    mAdapter.loadMoreComplete()
                } else {
                    srlLayout.isRefreshing = false
                    mAdapter.replaceData(dataList)
                }
            }

            override fun onError(error: String?) {
                LogUtil.e(error)
                if (isLoadMore) {
                    mAdapter.loadMoreFail()
                }
            }
        })
    }

    override fun onLoadMoreRequested() {
        doNetwork(true)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val itemData = adapter?.getItem(position) as DivListImgBean
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("url", itemData.detailsUrl)
        context.startActivity(intent)
    }

}