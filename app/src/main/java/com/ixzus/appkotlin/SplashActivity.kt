package com.ixzus.appkotlin

import android.graphics.Typeface
import android.os.Bundle
import android.view.WindowManager
import com.ixzus.appkotlin.base.BaseActivity
import com.ixzus.appkotlin.util.Sp
import com.ixzus.appkotlin.util.gotoActivity
import com.ixzus.appkotlin.util.toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    val SP_KEY = "check_update"
    var localVersion by Sp(this, SP_KEY, "0")

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        initEvent()
    }

    fun initView() {
        val font: Typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_wellcome.typeface = font
        tv_wellcome.text = "轻轻地一声问候"
    }

    fun initEvent() {
        iv_logo.setOnClickListener({
            gotoActivity<MainActivity>()
        })
        var countTime = 2L
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(countTime + 1)
                .map { aLong -> countTime - aLong }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                        goNext()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Long) {
                    }
                })
    }

    fun goNext() {
        var netVersion: String
        netVersion = "1"
        if (netVersion.toInt() > localVersion.toInt()) {
            toast("发现新版本，已升级新版本")
            localVersion = netVersion
        }
        gotoActivity<MainActivity>()
    }
}
