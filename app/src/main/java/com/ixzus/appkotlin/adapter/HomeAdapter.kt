package com.ixzus.appkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ixzus.appkotlin.R
import com.ixzus.appkotlin.entity.Meizi
import com.ixzus.appkotlin.util.inflate
import com.ixzus.appkotlin.util.loadUrl

/**
 * Created by huan on 2017/9/20.
 */
class HomeAdapter(val items: List<Meizi>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = parent.inflate(R.layout.rv_home)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(items[position].url)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        private var imageView = root.findViewById<ImageView>(R.id.iv_meizi)

        fun setImage(url: String) {
            imageView.loadUrl(url)
        }
    }
}

