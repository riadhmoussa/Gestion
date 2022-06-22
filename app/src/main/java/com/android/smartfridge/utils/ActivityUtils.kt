package com.android.smartfridge.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.android.smartfridge.ui.dialog.CustomProgressDialog

class ActivityUtils {


    companion object {
        inline fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
            return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
        }
        private var progressDialog: CustomProgressDialog? = null
        fun showCustomProgressDialog(activity: Activity?, isCancel: Boolean) {
            if (progressDialog != null && progressDialog!!.isShowing) {
                return
            }
            progressDialog = object : CustomProgressDialog(activity) {}
            if (activity != null) {
                if (!activity.isFinishing) (progressDialog as CustomProgressDialog).show()
            }
        }
        @SuppressLint("LogNotTimber")
        fun hideCustomProgressDialog() {
            try {
                if (progressDialog != null) {
                    progressDialog!!.dismiss()
                }
            } catch (e: Exception) {
                Log.d("tag", e.message.toString())
            }
        }

        inline fun ProgressBar.pVisible(visible: Boolean = false, invisible: Boolean = false) {
            visibility = when {
                visible -> View.VISIBLE
                invisible -> View.INVISIBLE
                else -> View.GONE
            }
        }

    }


}