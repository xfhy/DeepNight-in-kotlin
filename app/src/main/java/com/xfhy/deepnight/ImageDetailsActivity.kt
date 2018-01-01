package com.xfhy.deepnight

import com.bumptech.glide.Glide
import com.xfhy.deeplibrary.basekit.BaseActivity
import kotlinx.android.synthetic.main.activity_image_details.*
import kotlinx.android.synthetic.main.item_image_details_list.*

/**
 * @author xfhy
 * create at 2018/1/1 17:02
 * description：
 */
class ImageDetailsActivity : BaseActivity() {

    private var mUrl: String = ""

    override fun getContentViewResId(): Int = R.layout.activity_image_details

    override fun initIntentData() {
        mUrl = intent.getStringExtra("url")
    }

    override fun initView() {
        ivImage.setOnClickListener {
            finish()
        }
    }

    override fun initData() {
        Glide.with(mContext).load(mUrl).into(ivImage)
    }

}