package com.android.smartfridge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi


class App: Application() {
    companion object{
        var mContext: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}
