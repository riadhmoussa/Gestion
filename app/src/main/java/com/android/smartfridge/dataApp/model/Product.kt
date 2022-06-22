package com.android.smartfridge.dataApp.model

import java.io.Serializable
class Product (
    var uid:String,
    var name: String,
    var description: String,
    var ref: String,
    val price: String
)
    :Serializable
