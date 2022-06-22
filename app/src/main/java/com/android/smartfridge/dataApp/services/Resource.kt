package com.android.smartfridge.dataApp.services

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T): Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val isIOException: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val message: String?
    ): Resource<Nothing>()

    object Loading: Resource<Nothing>()
}
