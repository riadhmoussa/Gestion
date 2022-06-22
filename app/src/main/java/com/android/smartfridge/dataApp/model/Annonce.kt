package com.android.smartfridge.dataApp.model

import java.io.Serializable

class Annonce(
    var count_likes: Int = 0,
    var name: String,
    var photos: ArrayList<String> = ArrayList(),
    var date_annoce: String? = null,
    var price: String,
    val user: User? = null,
    var adresse: String ="",
) : Serializable
