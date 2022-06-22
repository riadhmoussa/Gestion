package com.android.smartfridge.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.android.smartfridge.R
import com.wang.avi.AVLoadingIndicatorView

abstract class CustomProgressDialog(context: Context?) :
    Dialog(context!!), View.OnClickListener {
    private var ivProgressBar: AVLoadingIndicatorView
    override fun onClick(v: View) {
    }
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_loader)
        ivProgressBar = findViewById<View>(R.id.avi) as AVLoadingIndicatorView
        ivProgressBar.show()
        val params = window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        window!!.attributes = params
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(false)
    }
}