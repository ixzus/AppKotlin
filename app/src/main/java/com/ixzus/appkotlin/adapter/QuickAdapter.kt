package com.ixzus.appkotlin.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ixzus.appkotlin.R
import com.ixzus.appkotlin.entity.Meizi
import com.ixzus.appkotlin.util.loadUrl

/**
 * Created by huan on 2017/9/21.
 */
class QuickAdapter(layoutResId: Int, data: List<Meizi>) : BaseQuickAdapter<Meizi, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: Meizi?) {
        val view = helper!!.getView<ImageView>(R.id.iv_meizi)
        view.loadUrl(item!!.url)
        helper.addOnClickListener(R.id.iv_meizi)
    }
}