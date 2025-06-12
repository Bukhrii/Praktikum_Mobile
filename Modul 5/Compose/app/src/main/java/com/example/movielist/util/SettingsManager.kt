package com.example.movielist.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Pastikan import ini benar untuk DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) {

    companion object {
        val LAYOUT_TYPE_KEY = booleanPreferencesKey("layout_type_is_grid") // true for GRID, false for LIST
        val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode") // true for dark, false for light
    }

    val layoutType: Flow<LayoutType> = context.dataStore.data
        .map { preferences ->
            // Pastikan memberikan nilai default jika key belum ada
            if (preferences[LAYOUT_TYPE_KEY] == true) LayoutType.GRID else LayoutType.LIST
        }

    suspend fun setLayoutType(type: LayoutType) {
        context.dataStore.edit { preferences ->
            preferences[LAYOUT_TYPE_KEY] = (type == LayoutType.GRID)
        }
    }

    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // Pastikan memberikan nilai default jika key belum ada
            preferences[IS_DARK_MODE_KEY] ?: false
        }

    suspend fun toggleDarkMode() {
        context.dataStore.edit { preferences ->
            val currentMode = preferences[IS_DARK_MODE_KEY] ?: false
            preferences[IS_DARK_MODE_KEY] = !currentMode
        }
    }
}

enum class LayoutType {
    LIST, GRID
}