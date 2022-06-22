package com.android.smartfridge.dataApp.model
import java.io.Serializable
class User (
    var uid:String,
    var profile_picture: String,
    var first_name: String,
    var last_name: String,
    var email: String,
    val phone: String,
    val matricule:String?=null,
    val adresse:String?=null
    )
    :Serializable
