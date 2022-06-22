package com.android.smartfridge.ui.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.smartfridge.adapter.AdapterFollowers
import com.android.smartfridge.base.BaseActivity
import com.android.smartfridge.base.BaseFragment

import dagger.hilt.android.AndroidEntryPoint

import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.repository.GestionApi
import com.android.smartfridge.databinding.FragmentNotificationsBinding
import com.android.smartfridge.databinding.ProfileFragmentBinding
import com.android.smartfridge.utils.Constants
import com.android.smartfridge.utils.DataRequestedEvent
import com.android.smartfridge.utils.Root
import com.android.smartfridge.utils.UserPreferences
import com.android.smartfridge.viewModels.ProductViewModel
import com.android.smartfridge.viewModels.VendorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus

class VendorFragment: BaseFragment<VendorViewModel, ProfileFragmentBinding,DataRepository>(){
    var adapter: AdapterFollowers?=null


    override fun getViewBinding(): ProfileFragmentBinding =
        ProfileFragmentBinding.inflate(layoutInflater)
    override fun getViewModel(): Class<VendorViewModel> = VendorViewModel::class.java
    override fun getFragmentRepository(): DataRepository {
        return DataRepository(
            remoteDataSource.buildApi(
                GestionApi::class.java, requireContext()
            )
        )
    }



    companion object;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mViewBinding.lifecycleOwner=this
        adapter=  AdapterFollowers(requireActivity() as BaseActivity<*, *,*>,null,false)
        var user2 = User("1","","Mouhamed ","Alaya","Alaya1177@gmail.com","52525252","14684721","Sfax")
        var user3 = User("1","","Ali","Trabelsi","benali@gmail.com","22222222","66974FG","Tunis")
        var user4 = User("1","","Majdoub","Test","test@gmail.com","43698742","FGD544","Sousse")
        adapter!!.listFollowers.add(user2)
        adapter!!.listFollowers.add(user3)
        adapter!!.listFollowers.add(user4)
        mViewBinding.rcvVendor.adapter=adapter

    }





}