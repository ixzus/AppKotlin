package com.ixzus.appkotlin.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException


/**
 * Created by huan on 2017/9/20.
 */
abstract class ApiCallback<M> : Observer<M> {

    val ERR_CODE: Int = 0

    abstract fun onSuccess(model: M)
    abstract fun onError(msg: String)

    override fun onSubscribe(d: Disposable) {
        //dialog start
    }


    override fun onNext(t: M) {
        onSuccess(t)
    }

    override fun onComplete() {
        //dialog end
    }


    override fun onError(t: Throwable) {

        var code: Int = 0
        var errorMessage: String = ""
        if (t is HttpException) {
            val meg = t.toString()   //
            code = t.code()
            errorMessage = t.message()
        } else if (t is SocketTimeoutException) {  //VPN open
            code = ERR_CODE
            errorMessage = "服务器响应超时"
        } else if (t is ConnectException) {
            code = ERR_CODE
            errorMessage = "网络连接异常，请检查网络"
        } else if (t is RuntimeException) {
            code = ERR_CODE
            errorMessage = "运行时错误"
        } else if (t is UnknownHostException) {
            code = ERR_CODE
            errorMessage = "无法解析主机，请检查网络连接"
        } else if (t is UnknownServiceException) {
            code = ERR_CODE
            errorMessage = "未知的服务器错误"
        } else if (t is IOException) {  //飞行模式等
            code = ERR_CODE
            errorMessage = "没有网络，请检查网络连接"
        }

        onFailure(code, errorMessage)
        onError(errorMessage)

    }

    fun onFailure(code: Int, message: String) {
        if (ERR_CODE == code) {
            //toast
        } else {
            disposeErrCode(code, message)
        }
    }

    private fun disposeErrCode(code: Int, message: String) {
        when (code) {
            302, 401, 404 -> {
                //tologin
            }
            500, 502 -> {
                //服务器开小差
            }
            else -> {
                //
            }


        }
    }
}