package com.android.smartfridge.utils

import com.android.smartfridge.dataApp.model.Categorie
import com.android.smartfridge.dataApp.model.User


class Constants {

    companion object {
        var isConnected: Boolean = true
        var user: User?=null
        var MaxTemp1 = "MAX31865_1"
        var MaxTemp2 = "MAX31865_2"
        var Temperature1 = "Temperature1"
        var Temperature2 = "Temperature2"
        var SmartFridge = "SmartFridge"
        var COMPT1 = "COMPT1001"
        var COMPT2 = "COMPT1002"
        val USER="USER"
        var listTypeSubCategorie=ArrayList<Categorie>()

    }
}