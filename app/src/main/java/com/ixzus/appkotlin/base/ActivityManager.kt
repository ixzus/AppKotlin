package com.ixzus.appkotlin.base

import android.app.Activity
import android.os.Build
import java.lang.ref.WeakReference


/**
 * Created by huan on 2017/9/20.
 */


class ActivityManager {
    private var mCurrentActivity: WeakReference<Activity>? = null

    open fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    open fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    open fun finishAllActivity() {
        for (activity in activityStack) {
            if (null != activity)
                activity.finish()
        }
        activityStack.clear()
    }

    open var currentActivity: Activity?
        get() {
            var curAct: Activity? = null
            if (mCurrentActivity != null) {
                curAct = mCurrentActivity!!.get()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (curAct == null || curAct.isDestroyed)
                        curAct = null
                }
            }
            return curAct
        }
        set(value) {
            this.mCurrentActivity = WeakReference<Activity>(value)
        }

    companion object {
        val instance = ActivityManager()
        private var activityStack = ArrayList<Activity>()
    }
}