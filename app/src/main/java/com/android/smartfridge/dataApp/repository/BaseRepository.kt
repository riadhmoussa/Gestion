package com.android.smartfridge.dataApp.repository

import com.android.smartfridge.dataApp.services.Resource
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRepository() {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is JsonSyntaxException -> {
                        Resource.Failure(false, true,null, null,throwable.message)
                    }
                    is SocketTimeoutException -> {
                        Resource.Failure(true, false,null, null,throwable.message)
                    }
                    is IOException -> {
                        Resource.Failure(false, true,null, null,throwable.message)
                    }
                    is HttpException -> {
                        Resource.Failure(false,false, throwable.code(), throwable.response()?.errorBody(),null)
                    }
                    else -> {
                        Resource.Failure(true,  false,null,null,null)
                    }
                }
            }
        }
    }



}
