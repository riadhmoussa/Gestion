package com.android.smartfridge.viewModels

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.utils.UserPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
@ExperimentalCoroutinesApi
class MainActivityViewModel  @Inject constructor(
    val activity: Activity,
     private val repository: DataRepository
) :
    ViewModel() {


}