package com.xfhy.deepnight.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author xfhy
 * create at 2018/1/1 15:12
 * description：
 */
class SpacesItemDecoration(var space: Int = 16) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.left = space
        outRect?.right = space
        outRect?.bottom = space
        if (parent?.getChildAdapterPosition(view) == 0) {
            outRect?.top = 12
        }
    }

}