package com.android.smartfridge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.smartfridge.adapter.AdapterFollowers
import com.android.smartfridge.base.BaseActivity
import com.android.smartfridge.base.BaseFragment
import com.android.smartfridge.dataApp.model.*
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.repository.GestionApi
import com.android.smartfridge.databinding.FragmentHomeBinding
import com.android.smartfridge.databinding.ProfileFragmentBinding
import com.android.smartfridge.viewModels.HomeViewModel
import com.android.smartfridge.viewModels.VendorViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
class CustomerFragment : BaseFragment<HomeViewModel, FragmentHomeBinding,DataRepository>() {
    var adapter: AdapterFollowers?=null
    private var auth : FirebaseAuth?= null


    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)
    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getFragmentRepository(): DataRepository {
        return DataRepository(
            remoteDataSource.buildApi(
                GestionApi::class.java, requireContext()
            )
        )
    }


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
        var user2 = User("1","","Fatma ","Alaya","Alaya1177@gmail.com","52525252")
        var user3 = User("1","","Aziz","Ben ali","benali@gmail.com","22222222")
        var user4 = User("1","","Test","Test","test@gmail.com","43698742")
        adapter!!.listFollowers.add(user2)
        adapter!!.listFollowers.add(user3)
        adapter!!.listFollowers.add(user4)
        mViewBinding.rcvFollowers.adapter=adapter

    }


}