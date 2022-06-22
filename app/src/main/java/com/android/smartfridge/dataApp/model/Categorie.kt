package com.android.smartfridge.dataApp.model

import java.io.Serializable

class Categorie(
    val id: Int,
    val name: String,
    val active: Boolean,
    val category: Categorie?,
    @Transient
    var isSelected:Boolean
):Serializable