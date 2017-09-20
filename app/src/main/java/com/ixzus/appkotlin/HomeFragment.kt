package com.ixzus.appkotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ixzus.appkotlin.adapter.HomeAdapter
import com.ixzus.appkotlin.entity.Meizi
import com.ixzus.appkotlin.net.ApiCallback
import com.ixzus.appkotlin.net.GankService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private var mParam1: String = ""
    private var mParam2: String = ""

    private var listData = ArrayList<Meizi>()
    private val adapter = HomeAdapter(listData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_info.text = mParam1
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
        GankService.api.getMeizi(50, 1)
                .subscribeOn(Schedulers.io())
                .map { it.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiCallback<List<Meizi>>() {
                    override fun onSuccess(model: List<Meizi>) {
                        listData.clear()
                        listData.addAll(model)
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(msg: String) {
                    }

                })
    }

}
