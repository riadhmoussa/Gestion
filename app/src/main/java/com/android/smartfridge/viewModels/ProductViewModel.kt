package com.android.smartfridge.viewModels

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.smartfridge.dataApp.model.Annonce
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.services.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject



@ExperimentalCoroutinesApi
class ProductViewModel  @Inject constructor(private val activity: Activity, private val repository: DataRepository) :
    ViewModel(){
    private val _getProductsResponse: MutableLiveData<Resource<ArrayList<Annonce>>> = MutableLiveData()
    val getProductsResponse: LiveData<Resource<ArrayList<Annonce>>>
        get() = _getProductsResponse
    fun getProducts() {
        viewModelScope.launch {
            _getProductsResponse.value = repository.getProducts()
        }
    }

}