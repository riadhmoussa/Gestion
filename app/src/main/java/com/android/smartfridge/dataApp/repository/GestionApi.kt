package com.android.smartfridge.dataApp.repository

import com.android.smartfridge.dataApp.model.Annonce
import com.android.smartfridge.dataApp.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface GestionApi {

    @GET("users.php")
    suspend fun getUsers(): ArrayList<User>

    @GET("users.php")
    suspend fun getVendors(): ArrayList<User>

    @GET("users.php")
    suspend fun getProducts(): ArrayList<Annonce>

}