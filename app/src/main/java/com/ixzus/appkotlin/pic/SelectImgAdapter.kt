package com.ixzus.appkotlin.pic

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.ixzus.appkotlin.R
import com.ixzus.appkotlin.entity.PicBean

import java.io.File
import java.util.ArrayList

class SelectImgAdapter(private val context: Context) : RecyclerView.Adapter<SelectImgAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater
    private var list: MutableList<PicBean> = ArrayList()
    private var selectMax = 9
    private var isUpdate: Boolean = false

    protected lateinit var mAddClickListener: OnAddClickListener

    interface OnAddClickListener {
        fun onAddClick()
    }

    fun setOnAddClickListener(mAddClickListener: OnAddClickListener) {
        this.mAddClickListener = mAddClickListener
    }

    protected var mItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mItemClickListener = listener
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    fun setUpdate(update: Boolean) {
        isUpdate = update
        notifyDataSetChanged()
    }

    fun setSelectMax(selectMax: Int) {
        this.selectMax = selectMax
    }

    fun setList(list: MutableList<PicBean>) {
        this.list = list
    }

    fun updateList(list: MutableList<PicBean>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var mImg: ImageView
        internal var mDel: ImageView

        init {
            mImg = view.findViewById(R.id.imageView)
            mDel = view.findViewById(R.id.del)
        }
    }

    override fun getItemCount(): Int {
        return if (isUpdate) {
            if (list.size < selectMax) {
                list.size + 1
            } else {
                list.size
            }
        } else {
            list.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isUpdate) {
            if (isShowAddItem(position)) {
                TYPE_CAMERA
            } else {
                TYPE_PICTURE
            }
        } else {
            TYPE_PICTURE
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.rv_select_pic,
                viewGroup, false)
        return ViewHolder(view)
    }

    private fun isShowAddItem(position: Int): Boolean {
        val size = if (list.size == 0) 0 else list.size
        return position == size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.mDel.visibility = View.GONE
            Glide.with(context).load(R.mipmap.ic_add).into(viewHolder.mImg)
            viewHolder.mImg.setOnClickListener { mAddClickListener.onAddClick() }
        } else {
            if (isUpdate) {
                viewHolder.mDel.visibility = View.VISIBLE
            } else {
                viewHolder.mDel.visibility = View.GONE
            }
            val imgurl = list[position].url
            if (imgurl!!.contains("http")) {
                Glide.with(context).load(imgurl).into(viewHolder.mImg)
            } else {
                Glide.with(context).load(File(imgurl)).into(viewHolder.mImg)
            }
            viewHolder.mDel.setOnClickListener {
                val index = viewHolder.adapterPosition
                if (index != RecyclerView.NO_POSITION) {
                    list.removeAt(index)
                    notifyItemRemoved(index)
                    notifyItemRangeChanged(index, list.size)
                }
            }
            if (null != mItemClickListener) {
                viewHolder.itemView.setOnClickListener { view ->
                    val position = viewHolder.adapterPosition
                    mItemClickListener!!.onItemClick(position, view)
                }
            }
        }
    }

    companion object {
        val TYPE_CAMERA = 1
        val TYPE_PICTURE = 2
    }

}
