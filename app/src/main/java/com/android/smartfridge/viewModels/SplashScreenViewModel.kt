package com.android.smartfridge.viewModels

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.android.smartfridge.dataApp.repository.DataRepository
import javax.inject.Inject

class SplashScreenViewModel  constructor(
    val activity: Activity,
     private val repository: DataRepository
    ) :
    ViewModel()