package com.android.smartfridge.viewModels

import android.app.Activity
import android.text.Editable
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.smartfridge.R
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.services.Resource
import com.android.smartfridge.utils.Root
import com.android.smartfridge.utils.UserPreferences
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject



class VendorViewModel  constructor(
    private val activity: Activity , private val repository: DataRepository

) :
    ViewModel(){
    private val _getVendorsResponse: MutableLiveData<Resource<ArrayList<User>>> = MutableLiveData()
    val getVendorsResponse: LiveData<Resource<ArrayList<User>>>
        get() = _getVendorsResponse
    fun getVendors() {
        viewModelScope.launch {
            _getVendorsResponse.value = repository.getVendors()
        }
    }

}