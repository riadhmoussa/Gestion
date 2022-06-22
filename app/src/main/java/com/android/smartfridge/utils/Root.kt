package com.android.smartfridge.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import com.android.smartfridge.ui.activities.*

class Root {
    companion object {
        fun unwrap(context: Context): Activity {
            var context: Context = context
            while (context !is Activity && context is ContextWrapper) {
                context = context.baseContext
            }
            return context as Activity
        }


        fun goToMainActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finishAffinity()
        }




    }
    }