package com.laboratoriosinteractivos.lab10.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class LocalProfileRepository(private val context: Context) {

    companion object {
        val NAME_KEY = stringPreferencesKey("user_name")
    }

    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
        }
    }

    val userNameFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY]
    }
}