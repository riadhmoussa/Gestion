package com.android.smartfridge.dataApp.services

import android.content.Context
import com.android.smartfridge.BuildConfig
import com.android.smartfridge.dataApp.repository.HttpInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {
    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2/fatma/")
            .client(
                getRetrofitClient(context)
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
            .create(api)
    }

    private fun getRetrofitClient(context: Context):OkHttpClient{
        var myInterceptor= HttpInterceptor(context)
        var okhttp= OkHttpClient().newBuilder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okhttp .addInterceptor(logging)
        }
        return  okhttp .addInterceptor(myInterceptor)
            .build()

    }



}
