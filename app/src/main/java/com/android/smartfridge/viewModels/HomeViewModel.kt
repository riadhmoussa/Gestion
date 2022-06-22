package com.android.smartfridge.viewModels

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import com.android.smartfridge.dataApp.model.*
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.services.Resource
import kotlinx.coroutines.launch


class HomeViewModel  constructor(
    private val activity: Activity
    , private val repository: DataRepository
) :
    ViewModel(){

    private val _getUsersResponse: MutableLiveData<Resource<ArrayList<User>>> = MutableLiveData()
    val getUsersResponse: LiveData<Resource<ArrayList<User>>>
        get() = _getUsersResponse
    fun getUsers() {
        viewModelScope.launch {
            _getUsersResponse.value = repository.getUsers()
        }
    }



}