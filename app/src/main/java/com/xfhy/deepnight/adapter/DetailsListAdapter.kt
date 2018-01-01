package com.xfhy.deepnight.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xfhy.basequickadapter.BaseQuickAdapter
import com.xfhy.basequickadapter.BaseViewHolder
import com.xfhy.deepnight.R
import java.util.*

/**
 * @author xfhy
 * create at 2018/1/1 16:36
 * description：
 */
class DetailsListAdapter(var layoutId: Int, var listData: ArrayList<String>)
    : BaseQuickAdapter<String, BaseViewHolder>(layoutId, listData) {

    private var itemHeight = 300

    override fun convert(holder: BaseViewHolder?, url: String?) {

        val layoutParams = holder?.itemView?.layoutParams
        val random = Random()
        layoutParams?.height = itemHeight + random.nextInt(400)
        holder?.itemView?.layoutParams = layoutParams

        Glide.with(holder?.itemView).load(url).into(holder?.getView(R.id.ivGirlImage) as ImageView)
    }

    fun setDefaultHeight(height: Int) {
        itemHeight = height
    }

    private fun <E, T> java.util.ArrayList<E>.addAll(elements: ArrayList<T>) {
        elements.forEach {
            add(it as E)
        }
    }

    fun <String> replaceData(dataList: ArrayList<String>) {
        if (dataList !== this.listData) {
            this.listData.clear()
            this.listData.addAll(dataList)
        }
        this.notifyDataSetChanged()
    }

}