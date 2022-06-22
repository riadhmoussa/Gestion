package com.android.smartfridge.utils

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.android.smartfridge.dataApp.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class  UserPreferences(context: Context) {
    companion object {
        private const val DATA_SMARTFRIDGE_NAME = "user_data_store"
        private val KEY_AUTH = preferencesKey<String>("token")
        private val KEY_USER = preferencesKey<String>("user")
         val KEY_COMP1= preferencesKey<String>("compressor1")
         val KEY_COMP2= preferencesKey<String>("compressor2")
        private val KEY_FCM = preferencesKey<String>("fcm")
        private val KEY_ISLOGED = preferencesKey<Boolean>("isLogedIn")
        val KEY_ISSTARTCOMP1 = preferencesKey<Boolean>("KEY_ISSTARTCOMP1")
        val KEY_ISSTARTCOMP2 = preferencesKey<Boolean>("KEY_ISSTARTCOMP2")

        val KEY_MaximumTemperature1 = preferencesKey<Boolean>("KEY_MaximumTemperature1")
        val KEY_SwitchOffTemperature1 = preferencesKey<Boolean>("KEY_SwitchOffTemperature1")

        val KEY_MaximumTemperature2 = preferencesKey<Boolean>("KEY_MaximumTemperature2")
        val KEY_SwitchOffTemperature2 = preferencesKey<Boolean>("KEY_SwitchOffTemperature2")


    }

    private val appContext = context
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = appContext.createDataStore(
            name = DATA_SMARTFRIDGE_NAME,
            migrations = listOf(SharedPreferencesMigration(context, "oldProfilePreferences"))
        )
    }

    val authToken: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH]
    }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }
    suspend fun saveISLogedIn(isLogedIn:Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_ISLOGED] = isLogedIn
        }
    }
    val IsLoggedIn: Flow<Boolean?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_ISLOGED]
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            var gson = Gson()
            var json = gson.toJson(user)
            preferences[KEY_USER] = json
        }
    }
    suspend fun saveComp1(start:String,comp:Any) {
        dataStore.edit { preferences ->
            preferences[comp as Preferences.Key<Any>] = start
        }
    }

    val Compresser1: Flow<String?>
        get()=
            dataStore.data.map { preferences ->
                preferences[KEY_COMP1]
            }

    val USER: Flow<String?>
    get()=
        dataStore.data.map { preferences ->
            preferences[KEY_USER]
        }
    suspend fun clearDataCash() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
    suspend fun saveComp2(start:String,comp:Any) {
        dataStore.edit { preferences ->
            preferences[comp as Preferences.Key<Any>] = start
        }
    }
    val Compresser2: Flow<String?>
        get()=
            dataStore.data.map { preferences ->
                preferences[KEY_COMP2]
            }
    suspend fun saveIsStartComp(start:Boolean,comp:Any) {
        dataStore.edit { preferences ->
            preferences[comp as Preferences.Key<Any>] = start
        }
    }
    val IsCompt1Start: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_ISSTARTCOMP1]
        }
    val IsCompt2Start: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_ISSTARTCOMP2]
        }

    val IsMaximumTemperature1: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_MaximumTemperature1]
        }
    val IsSwitchOffTemperature1: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_SwitchOffTemperature1]
        }


    val IsMaximumTemperature2: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_MaximumTemperature2]
        }
    val IsSwitchOffTemperature2: Flow<Boolean?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_SwitchOffTemperature2]
        }

    suspend fun saveFCMToken(FCMToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_FCM] = FCMToken
        }
    }



    val FCMToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_FCM]
        }
}
