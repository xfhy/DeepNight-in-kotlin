package com.xfhy.deepnight.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xfhy.basequickadapter.BaseQuickAdapter
import com.xfhy.basequickadapter.BaseViewHolder
import com.xfhy.deeplibrary.common.dp2px
import com.xfhy.deepnight.R
import com.xfhy.deepnight.bean.DivListImgBean
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author xfhy
 * create at 2018/1/1 14:39
 * description：外层列表页
 */
class HomeListAdapter(var layoutId: Int, var listData: ArrayList<DivListImgBean>)
    : BaseQuickAdapter<DivListImgBean, BaseViewHolder>(layoutId, listData) {

    private var itemHeight = 300

    override fun convert(holder: BaseViewHolder?, bean: DivListImgBean?) {
        val layoutParams = holder?.itemView?.layoutParams
        val random = Random()
        layoutParams?.height = itemHeight + random.nextInt(400)
        holder?.itemView?.layoutParams = layoutParams

        Glide.with(holder?.itemView).load(bean?.src).into(holder?.getView(R.id.ivGirlImage) as ImageView)
        holder.setText(R.id.tvImageTitle, bean?.text)
    }

    fun setDefaultHeight(height: Int) {
        itemHeight = height
    }

    fun <DivListImgBean> replaceData(data: ArrayList<DivListImgBean>) {
        if (data !== this.listData) {
            this.listData.clear()
            this.listData.addAll(data)
        }
        this.notifyDataSetChanged()
    }

    private fun <E, T> java.util.ArrayList<E>.addAll(elements: ArrayList<T>) {
        elements.forEach {
            add(it as E)
        }
    }

    fun <DivListImgBean> addData(dataList: ArrayList<DivListImgBean>) {
        this.mData.addAll(dataList)
        this.notifyItemRangeInserted(this.mData.size - dataList.size + this.headerLayoutCount, dataList.size)
    }

    private fun <E, T> MutableList<E>.addAll(elements: ArrayList<T>) {
        elements.forEach {
            add(it as E)
        }
    }


}



