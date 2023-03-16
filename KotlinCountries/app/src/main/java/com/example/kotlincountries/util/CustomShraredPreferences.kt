package com.example.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomShraredPreferences {

    companion object {
        private val PREFERENCES_TIME = "preferences time"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance: CustomShraredPreferences? = null

        operator fun invoke(context: Context) : CustomShraredPreferences =
            instance?: synchronized(Any(),){
                instance?: makeCustomSharedPrefences(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPrefences(context: Context) : CustomShraredPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomShraredPreferences()
        }
    }

    fun saveTime (time : Long){
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTıme() = sharedPreferences?.getLong(PREFERENCES_TIME,0)
}