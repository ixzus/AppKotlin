package com.ixzus.appkotlin.base

import com.ixzus.appkotlin.entity.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by huan on 2017/9/20.
 */
abstract class BaseEventActivity : AbsActivity() {

    abstract fun onEventResult(messageEvent: MessageEvent)

    fun postEvent(code: Int, msg: String) {
        var messageEvent = MessageEvent(code, msg)
        EventBus.getDefault().post(messageEvent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(messageEvent: MessageEvent) {
        onEventResult(messageEvent)
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}