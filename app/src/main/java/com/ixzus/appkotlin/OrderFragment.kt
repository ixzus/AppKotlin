package com.ixzus.appkotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ixzus.appkotlin.adapter.QuickAdapter
import com.ixzus.appkotlin.entity.Meizi
import com.ixzus.appkotlin.net.ApiCallback
import com.ixzus.appkotlin.net.GankService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {

    private var mParam1: String = ""
    private var mParam2: String = ""

    private lateinit var noDataView: View
    private var mPageNum = 1
    private val mPageSize = 8

    private var listData = ArrayList<Meizi>()
    private val adapter = QuickAdapter(R.layout.rv_home, listData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_order, container, false)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): OrderFragment {
            val fragment = OrderFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    fun initRecyclerView() {
        refreshlayout.setOnRefreshListener({
            loadData(1)
        })
        refreshlayout.setOnLoadmoreListener {
            loadData(++mPageNum)
        }
        recyclerview.layoutManager = LinearLayoutManager(activity)
        noDataView = layoutInflater.inflate(R.layout.view_nodata, recyclerview.parent as ViewGroup, false)
        noDataView.setOnClickListener({
            loadData(1)
            Toast.makeText(activity, "click refresh", Toast.LENGTH_SHORT).show()
        })
        adapter.openLoadAnimation()
        adapter.setOnItemClickListener { adapter, view, position ->
            Toast.makeText(activity, "onItemClick" + position + adapter.data[position], Toast.LENGTH_SHORT).show()
        }
        recyclerview.adapter = adapter
        adapter.emptyView = noDataView


    }

    fun loadData(pageNum: Int) {
        mPageNum = pageNum
        GankService.api.getMeizi(mPageSize, mPageNum)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiCallback<List<Meizi>>() {
                    override fun onSuccess(model: List<Meizi>) {
                        if (model!!.size > 0) {
                            if(1==mPageNum) {
                                adapter.setNewData(model)
                            }else{
                                adapter.addData(model)
                            }
                        } else {
                            adapter.emptyView = noDataView
                        }
                        refreshFinish()
                    }

                    override fun onError(msg: String) {
                        adapter.emptyView = noDataView
                        refreshFinish()
                    }

                })
    }

    fun refreshFinish(){
        if (1 == mPageNum) {
            refreshlayout.finishRefresh()
        } else {
            refreshlayout.finishLoadmore()
        }
    }
}