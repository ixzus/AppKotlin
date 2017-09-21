package com.ixzus.appkotlin.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ixzus.appkotlin.R

/**
 * Created by huan on 2017/9/21.
 */
class HomeAdapter(layoutResId: Int, data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        val view = helper!!.getView<TextView>(R.id.tv_name)
        view.text = item
        helper.addOnClickListener(R.id.tv_name)
    }
}