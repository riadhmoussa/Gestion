package com.android.smartfridge.utils

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Context
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.android.smartfridge.R
import com.android.smartfridge.dataApp.model.User
import com.android.smartfridge.databinding.ToastLayoutBinding
import com.android.smartfridge.utils.Root.Companion.unwrap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class Utils {
    companion object {

        fun ConvertInputToStringNoChange(inputStream: InputStream): String? {
            val bureader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            var linereultcal: String? = ""
            try {
                while (bureader.readLine().also { line = it } != null) {
                    linereultcal += line
                }
                inputStream.close()
            } catch (ex: Exception) {
            }
            return linereultcal
        }


        fun showTimePicker(context: Context, method: (String,String) -> Unit) {
            var hour = 0
            var minute = 0
            val mcurrentTime = Calendar.getInstance()
            hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker =
                TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                        var heur = ""
                        var min = ""
                        if (hourOfDay < 10) heur = "0" + hourOfDay
                        else heur = "" + hourOfDay

                        if (minute < 10) min = "0" + minute
                        else min = "" + minute

                        method(heur , min)



                    }
                }, hour, minute, true)
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

        @JvmStatic
        @BindingAdapter("setLayoutWidth")
        fun setLayoutWidth(view: View, width: Float) {
            view.layoutParams = view.layoutParams.apply { this.width = width.toInt() }
        }
        @JvmStatic
        @BindingAdapter("app:loadBackgroundImageCorner")
        fun loadBackgroundImageCorner(view: ImageView, path: String?) {
            var url: URL? = null
            if (path != null) {
                var newPath: Any? = null
                if (InputControl.isInteger(path)) {
                    newPath = path.toInt()
                } else {
                    /* if (!path.isNullOrEmpty() && URLUtil.isValidUrl(path)) {
                         url = URL(path)
                         var newUrl = URL("https", url.getHost(), url.port, url.getFile())
                         newPath = newUrl.toString()
                     }*/
                    newPath = path
                }
                if (newPath != null) {
                    //RequestOptions.bitmapTransform( RoundedCornersTransformation(40, 0, RoundedCornersTransformation.CornerType.TOP)).centerCrop()
                    val options =
                        RequestOptions().timeout(100000000).centerCrop()
                    Glide.with(unwrap(view.context).baseContext).load(newPath)
                        .into(view)
                    /* .into(object : SimpleTarget<Drawable>() {
                         override fun onResourceReady(
                             resource: Drawable, transition: Transition<in
                             Drawable>?
                         ) {
                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                 view.background = resource
                             }
                         }
                     }
                     )*/



                }
            } else {

            }
        }

        @JvmStatic
        @BindingAdapter("setCornerWith")
        fun setCornerWith(img: CircleImageView, with: Float) {
            if (with > 0) img.borderWidth = with.toInt()
        }

        @JvmStatic
        @BindingAdapter("setCornerValue")
        fun setCornerValue(card: CardView, with: Float) {
            card.radius = with / 2

        }

        @JvmStatic
        @BindingAdapter(value = ["user", "width"], requireAll = true)
        fun setTextName(textView: TextView, user: User?,width:Float) {
            textView.textSize=width/8
            if (user != null) {
                textView.text = user.first_name.substring(0, 1) + user.last_name.substring(0, 1)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["app:layout_marginEnd", "app:layout_marginStart"])
        fun setLayoutMargin(
            view: View,
            marginEnd: Float,
            marginStart: Float
        ) {
            val parameter: ConstraintLayout.LayoutParams =
                view.layoutParams as ConstraintLayout.LayoutParams
            if (Locale.getDefault().language == "ar") {
                parameter.setMargins(
                    marginEnd.toInt(), parameter.topMargin, marginStart.toInt(),
                    parameter.bottomMargin
                )

            } else {
                parameter.setMargins(
                    marginStart.toInt(), parameter.topMargin, marginEnd.toInt(),
                    parameter.bottomMargin
                )
            }
            view.layoutParams = parameter
        }

        fun Activity.hideKeyboard() {
           // hideKeyboard((if (currentFocus == null) View(this) else currentFocus)!!)
        }
        fun showSucessToast(activity: Activity, message: String) {
                var binding: ToastLayoutBinding? = null
                binding = DataBindingUtil.inflate(
                    activity.layoutInflater,
                    R.layout.toast_layout,
                    activity.findViewById(R.id.toast_layout_root) as ViewGroup?,
                    false
                )
                binding.isSuccess = true
                binding.messageTxt.text = message
                val toast = Toast(activity.applicationContext)
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                toast.duration = Toast.LENGTH_LONG
                toast.view = binding.root
                toast.setGravity(Gravity.BOTTOM, 0, 40)
            toast.show()
                toast.view!!.isShown
                Handler().postDelayed({

                }, 3500)

        }

    }
}