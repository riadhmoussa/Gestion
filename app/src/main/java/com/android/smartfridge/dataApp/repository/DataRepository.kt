package com.android.smartfridge.dataApp.repository

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton



class DataRepository @Inject constructor(private val api: GestionApi) :
    BaseRepository() {
    suspend fun getUsers() = safeApiCall {
        api.getUsers()
    }

    suspend fun getVendors() = safeApiCall {
        api.getVendors()
    }

    suspend fun getProducts() = safeApiCall {
        api.getProducts()
    }


}
