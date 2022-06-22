package com.android.smartfridge.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.smartfridge.dataApp.repository.BaseRepository
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.viewModels.*

class ViewModelFactory(
    private val activity: BaseActivity<*,*,*>,
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(activity,repository as DataRepository) as T
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(activity,repository as DataRepository) as T
            modelClass.isAssignableFrom(ProductViewModel::class.java) -> ProductViewModel(activity,repository as DataRepository) as T
            modelClass.isAssignableFrom(SplashScreenViewModel::class.java) -> SplashScreenViewModel(activity,repository as DataRepository) as T
            modelClass.isAssignableFrom(VendorViewModel::class.java) -> VendorViewModel(activity,repository as DataRepository) as T

            else -> throw IllegalArgumentException("View Model Class Not Found")
        }
    }
}