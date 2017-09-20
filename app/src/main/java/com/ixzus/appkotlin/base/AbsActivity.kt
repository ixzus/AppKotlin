package com.ixzus.appkotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by huan on 2017/9/20.
 */
abstract class AbsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.instance.addActivity(this)
    }

    override fun isDestroyed(): Boolean {
        return super.isDestroyed()
        ActivityManager.instance.removeActivity(this)
    }
}