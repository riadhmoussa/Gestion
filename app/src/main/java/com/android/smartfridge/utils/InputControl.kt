package com.android.smartfridge.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Base64
import android.util.Patterns
import android.webkit.MimeTypeMap
import android.widget.EditText
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class InputControl {
    companion object {
        fun isValidEmail(target: EditText): Boolean {
            if (TextUtils.isEmpty(target.text.toString())
            ) {
                // target.setError(target.context.getString(R.string.bazary_86));
                return false
            }
            if (!TextUtils.isEmpty(target.text.toString()) && Patterns.EMAIL_ADDRESS.matcher(target.text.toString())
                    .matches()
            ) {
                return true
            }
            //  target.setError(target.context.getString(R.string.bazary_87));
            return false
        }
        /* fun isFrenchPhone(target: EditText): Boolean {
             var phoneExp = "^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})\$".toRegex()
             if (!TextUtils.isEmpty(target.text.toString()) && target.text.toString().matches(phoneExp)
             ) {
                 return true
             }
             target.setError("Numero Invalide!");
             return false
         }*/
        fun isValidLengthPhone(input_ed: EditText,min:Int): Boolean {
            var max:Int=10
            if (input_ed.text.toString().trim()
                    .isEmpty() || input_ed.text.toString().length < min || input_ed.text.toString().length > max
            ) {
                if (input_ed.text.length < min && input_ed.text.length >10) {
                    input_ed.error = "your phone number must be great than"+min+" and less than "+max+"numbers"

                }
                return false

            }
            return true
        }




        @Throws(IOException::class)
        fun getBase64FromUri(path: String?): String? {

            // return Base64.encodeToString(getBytesFromFile(path), Base64.DEFAULT);
            var bitmapOrg = BitmapFactory.decodeFile(path)
            val bao = ByteArrayOutputStream()
            bitmapOrg = Bitmap.createScaledBitmap(
                bitmapOrg,
                bitmapOrg.width * 256 / bitmapOrg.height, 256,
                true
            )
            bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 80, bao)
            val ba = bao.toByteArray()
            // String ba1 = Base64.encodeBytes(ba);
            return Base64.encodeToString(ba, Base64.DEFAULT)
        }
        fun getMimeType(context: Context, uri: Uri): String? {
            val extension: String?

            //Check uri format to avoid null
            extension = if (uri.scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                //If scheme is a content
                val mime = MimeTypeMap.getSingleton()
                mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
            } else {
                //If scheme is a File
                //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
                MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(uri.path)).toString())
            }
            return extension
        }

        fun getBase64FromBitmap(bitmapOrg: Bitmap?): String? {
            var bitmapOrg = bitmapOrg
            val bao = ByteArrayOutputStream()
            return if (bitmapOrg != null) {
                bitmapOrg = Bitmap.createScaledBitmap(
                    bitmapOrg,
                    bitmapOrg.width * 800 / bitmapOrg.height,
                    800, true
                )
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 80, bao)
                val ba = bao.toByteArray()
                Base64.encodeToString(ba, Base64.DEFAULT)
            } else {

                null
            }
        }


        fun isInteger(str: String?): Boolean {
            if (str == null) {
                return false
            }
            val length = str.length
            if (length == 0) {
                return false
            }
            var i = 0
            if (str[0] == '-') {
                if (length == 1) {
                    return false
                }
                i = 1
            }
            while (i < length) {
                val c = str[i]
                if (c < '0' || c > '9') {
                    return false
                }
                i++
            }
            return true
        }

    }


}
