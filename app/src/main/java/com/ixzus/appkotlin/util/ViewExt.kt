package com.ixzus.appkotlin.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

/**
 * Created by huan on 2017/9/19.
 */

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    var toast: Toast = Toast.makeText(this, message, duration)
    toast.setGravity(Gravity.CENTER, 0, 0)
    return toast
}

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}


inline fun <reified T : Activity> Activity.gotoActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun loge(msg: String, tag: String = "AppKotlin") = Log.e(tag, msg)

val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot:Boolean = false):View{
    return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
}

@ColorInt
fun Context.getColor(@ColorRes color: Int): Int {
    return this.resources.getColor(color)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}


inline fun DrawerLayout.consume(f: () -> Unit): Boolean {
    f()
    closeDrawers()
    return true
}

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}


val ViewGroup.views: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

