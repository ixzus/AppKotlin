package com.ixzus.appkotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.ixzus.appkotlin.util.newIntent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

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
            newIntent<MainActivity>()
        })
        var countTime = 2L
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(countTime + 1)
                .map { aLong -> countTime - aLong }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onNext(t: Long?) {
                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onComplete() {
                        goNext()
                    }
                })

    }

    fun goNext() {
        newIntent<MainActivity>()
    }
}
