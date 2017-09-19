package com.ixzus.appkotlin

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Toast
import com.ixzus.appkotlin.util.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var homeFragment: HomeFragment? = null
    var orderFragment: HomeFragment? = null
    var myFragment: HomeFragment? = null
    var mExitTime: Long = 0
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
        initFragment(savedInstanceState)
    }

    fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val mFragment: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragment) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment.newInstance("homeFragment", "0")
            orderFragment = HomeFragment.newInstance("orderFragment", "0")
            myFragment = HomeFragment.newInstance("myFragment", "0")
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, homeFragment)
            fragmentTrans.add(R.id.fl_content, orderFragment)
            fragmentTrans.add(R.id.fl_content, myFragment)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(orderFragment)
                .hide(myFragment)
                .commit()
    }

    fun selectIndex(index: Int) {
        tabStatus(index)
        when (index) {
            1 -> {
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(orderFragment)
                        .hide(myFragment)
                        .commit()
            }
            2 -> {
                supportFragmentManager.beginTransaction().show(orderFragment)
                        .hide(homeFragment)
                        .hide(myFragment)
                        .commit()
            }
            3 -> {
                supportFragmentManager.beginTransaction().show(myFragment)
                        .hide(homeFragment)
                        .hide(orderFragment)
                        .commit()
            }
        }
    }

    fun initEvent() {
        tv_tab1.setOnClickListener { view -> selectIndex(1) }
        tv_tab2.setOnClickListener { view -> selectIndex(2) }
        tv_tab3.setOnClickListener { view -> selectIndex(3) }
        tabStatus(1)
    }

    fun tabStatus(index: Int) {
        when (index) {
            1 -> {
                tv_tab1.isSelected = true
                tv_tab2.isSelected = false
                tv_tab3.isSelected = false
                tv_tab1.setTextColor(Color.BLUE)
                tv_tab2.setTextColor(Color.GRAY)
                tv_tab3.setTextColor(Color.GRAY)
            }
            2 -> {
                tv_tab1.isSelected = false
                tv_tab2.isSelected = true
                tv_tab3.isSelected = false
                tv_tab1.setTextColor(Color.GRAY)
                tv_tab2.setTextColor(Color.BLUE)
                tv_tab3.setTextColor(Color.GRAY)
            }
            3 -> {
                tv_tab1.isSelected = false
                tv_tab2.isSelected = false
                tv_tab3.isSelected = true
                tv_tab1.setTextColor(Color.GRAY)
                tv_tab2.setTextColor(Color.GRAY)
                tv_tab3.setTextColor(Color.BLUE)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        toast?.let { toast!!.cancel() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                toast = showToast("再按一次退出")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
