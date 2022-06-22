package com.android.smartfridge.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.android.smartfridge.R
import com.android.smartfridge.base.BaseActivity
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.dataApp.repository.DataRepository
import com.android.smartfridge.dataApp.repository.GestionApi
import com.android.smartfridge.databinding.ActivityMainBinding
import com.android.smartfridge.ui.fragments.CustomerFragment
import com.android.smartfridge.ui.fragments.ProductsFragment
import com.android.smartfridge.ui.fragments.VendorFragment
import com.android.smartfridge.utils.DataRequestedEvent
import com.android.smartfridge.utils.UserPreferences
import com.android.smartfridge.viewModels.MainActivityViewModel
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe



 class MainActivity :  BaseActivity<MainActivityViewModel,ActivityMainBinding, DataRepository>() , NavigationView.OnNavigationItemSelectedListener {
   private var connectedUser:User?=null
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java


    override fun getActivityRepository(): DataRepository {
        return DataRepository(
            remoteDataSource.buildApi(
                GestionApi::class.java, this
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        EventBus.getDefault().register(this)
        mViewBinding.lifecycleOwner=this
        mViewBinding.activity=this
        mViewBinding.viewModel = mViewModel

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        if(nav_view!=null){
            setUpNavHeader(nav_view)
        }

        displayScreen( R.id.nav_home )

}

    private  fun setUpNavHeader(navigationView: NavigationView?) {
        val headerView: View = navigationView!!.getHeaderView(0)
        var navUsername:TextView= headerView.findViewById(R.id.navUsername)
        var navEmail:TextView= headerView.findViewById(R.id.navEmail)
        var userPreferences=UserPreferences(this)
        if(userPreferences.USER!=null){
            runBlocking {
                var userstring=userPreferences.USER.first()
                val gson = Gson()
                 connectedUser = gson.fromJson<User>(userstring, User::class.java)
                if(connectedUser!=null){
                    navUsername.text = connectedUser!!.first_name+" "+ connectedUser!!.last_name
                    navEmail.text = connectedUser!!.email
                }

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun displayScreen(id: Int){
        when (id){
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, CustomerFragment()).commit()
            }

            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, VendorFragment()).commit()

            }
            R.id.nav_logout -> {
                supportFragmentManager.beginTransaction().replace(R.id.relativelayout, ProductsFragment()).commit()

            }


        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      displayScreen(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    @SuppressLint("NewApi")
    @Subscribe
    fun onEvent(event: DataRequestedEvent?) {
        if (event != null) {
            if (event.message == "changeUser") {
                if(nav_view!=null){
                    setUpNavHeader(nav_view)
                }

            }

        }
    }



 }