package com.android.smartfridge.dataApp.services

import com.android.smartfridge.dataApp.model.Annonce
import com.android.smartfridge.dataApp.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface GestionService {
    companion object {
        const val users = "users.php"
        const val vendors = "users.php"
        const val products = "users.php"
    }

    @GET(users)
    suspend fun getUsres(
    ): ArrayList<User>

    @GET(vendors)
    suspend fun getVendors(

    ): ArrayList<User>

    @GET(products)
    suspend fun getProducts(

    ):ArrayList<Annonce>

}
