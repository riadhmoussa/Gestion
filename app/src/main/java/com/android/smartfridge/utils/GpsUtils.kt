package com.android.smartfridge.utils

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.smartfridge.base.BaseActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

class GpsUtils(private val activity: Activity) {
    companion object{
        fun openGPSSetting(source: Any) {
            var intent= Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            if (source is Activity)
                source.startActivityForResult(intent,889)
            else ( source as Fragment).startActivityForResult(intent,889)
        }
    }
    private val mSettingsClient: SettingsClient
    private val mLocationSettingsRequest: LocationSettingsRequest
    private val locationManager: LocationManager
    private val locationRequest: LocationRequest
    fun checkLocationEnable():Boolean{
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
    // method for turn on GPS
    fun turnGPSOn(onGpsListener: onGpsListener?) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGpsListener?.gpsStatus(true)
        } else {
            mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnCanceledListener(activity){
                    Toast.makeText(
                        activity,
                        "Complet",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnSuccessListener(activity) {
                    //  GPS is already enable, callback GPS status through listener
                    Toast.makeText(
                        activity,
                        "GPS Connected",
                        Toast.LENGTH_LONG
                    ).show()
                    onGpsListener?.gpsStatus(true)
                }
                .addOnFailureListener(
                    activity
                ) { e ->
                    val statusCode = (e as ApiException).statusCode
                    when (statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try { // Show the dialog by calling startResolutionForResult(), and check the
                            // result in onActivityResult().
                            val rae = e as ResolvableApiException
                            rae.startResolutionForResult(
                                activity,
                                889
                            )
                        } catch (sie: IntentSender.SendIntentException) {
                            Log.i(
                                ContentValues.TAG,
                                "PendingIntent unable to execute request."
                            )
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            val errorMessage =
                                "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings."
                            Log.e(ContentValues.TAG, errorMessage)
                            Toast.makeText(
                                activity,
                                errorMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
        }
    }

    interface onGpsListener {
        fun gpsStatus(isGPSEnable: Boolean)
    }

    init {
        ( activity as BaseActivity<*, *,*>).supportFragmentManager.fragments
        locationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mSettingsClient = LocationServices.getSettingsClient(activity)
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10 * 1000.toLong()
        locationRequest.fastestInterval = 2 * 1000.toLong()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        mLocationSettingsRequest = builder.build()
        //**************************
        builder.setAlwaysShow(true) //this is the key ingredient
        //**************************
    }


}