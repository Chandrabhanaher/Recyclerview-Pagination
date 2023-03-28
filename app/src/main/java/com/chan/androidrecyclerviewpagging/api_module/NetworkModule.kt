package com.chan.androidrecyclerviewpagging.api_module

import com.google.gson.GsonBuilder
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
object NetworkModule {

    //var SHOE_URL:String ="http://192.168.1.103/pgdemo/"
    var HOST_URL:String ="https://chandrabhanaher.000webhostapp.com/"

    val getClient: ApiInterface
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.NONE }

            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor{ chain ->
                    val original  = chain.request()
                    val requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .method(original.method, original.body)
                        .cacheControl(
                            CacheControl.Builder()
                                .maxAge(0, TimeUnit.SECONDS) //noCache()
                                .build())
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(HOST_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }


}
