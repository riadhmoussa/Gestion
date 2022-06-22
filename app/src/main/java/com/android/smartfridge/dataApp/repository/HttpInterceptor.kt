package com.android.smartfridge.dataApp.repository

import android.content.Context
import com.android.smartfridge.dataApp.services.Resource
import com.android.smartfridge.utils.Constants
import com.android.smartfridge.utils.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import retrofit2.Retrofit
import java.io.IOException
import okhttp3.Response



class HttpInterceptor(val context: Context) : Interceptor,BaseRepository() {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (Constants.isConnected) {
            var preferences = UserPreferences(context)
            return runBlocking {


                var request = chain.request().newBuilder().also {
                    it.addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "*/*")
                }.build()

                val response: Response = chain.proceed(request)
                return@runBlocking response
            }
        }
        else {
            // (Root.unwrap(context) as BaseActivity<*,*>).testConnection(chain)
            throw NoNetworkException(context)
        }
    }

}
class NoNetworkException internal constructor(context: Context) : IOException("")
