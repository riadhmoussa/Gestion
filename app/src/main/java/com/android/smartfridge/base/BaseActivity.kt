package com.android.smartfridge.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.android.smartfridge.dataApp.repository.BaseRepository
import com.android.smartfridge.dataApp.services.RemoteDataSource
import java.io.Serializable

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding, R: BaseRepository>  : AppCompatActivity(),
    Serializable {

    protected lateinit var mViewModel: VM
    var TAG = this.javaClass.simpleName
    protected val remoteDataSource = RemoteDataSource()

    lateinit var mViewBinding: VB


    init {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        val factory = ViewModelFactory(this,getActivityRepository())
        mViewModel = ViewModelProvider(this, factory)[getViewModel()]
        changeStatusBarColor(android.R.color.transparent)


    }
    /**
     * It returns [VB] which is assigned to [mViewBinding] and used in [onCreate]
     */
    abstract fun getViewBinding(): VB
    abstract fun getActivityRepository(): R
    abstract fun getViewModel(): Class<VM>

    fun changeStatusBarColor(color:Int) {
        val window: Window = this.window

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
        val winParams = window.attributes
        winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        window.attributes = winParams
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    }
    fun hideStatusBar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun Activity.hideKeyboard() {
        hideKeyboard((if (currentFocus == null) View(this) else currentFocus)!!)
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }


    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    override fun onBackPressed() {
        finish()
    }


}

fun Context.getDimension(@DimenRes resId: Int): Float {
    return resources.getDimension(resId)
}
