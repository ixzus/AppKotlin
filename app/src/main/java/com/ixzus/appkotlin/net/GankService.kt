package com.ixzus.appkotlin.net

import com.ixzus.appkotlin.BuildConfig
import com.ixzus.appkotlin.entity.Meizi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by huan on 2017/9/20.
 */
class GankService {

    data class ResponseWrapper<T>(val error: Boolean, val results: List<T>)

    interface Apis {
        @GET("{count}/{pageNum}")
        fun getMeizi(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<ResponseWrapper<Meizi>>
    }

    companion object {
        val API_HOST_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/"
        val api: Apis

        init {
            val builder = OkHttpClient.Builder()
            if(BuildConfig.DEBUG){
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }
            val okHttpClient = builder.build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            api = retrofit.create(Apis::class.java)
        }
    }
}