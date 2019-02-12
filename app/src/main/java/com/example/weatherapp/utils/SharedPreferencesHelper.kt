package com.example.weatherapp.utils

import android.content.Context
import com.example.weatherapp.models.City
import com.example.weatherapp.models.Constants.CITIES_PREFS_FILENAME
import com.google.gson.Gson

class SharedPreferencesHelper(var context: Context) {


//    fun getCityFromPreferences(): City {
//        var prefs = context.getSharedPreferences(CITIES_PREFS_FILENAME, Context.MODE_PRIVATE)
//        var citiesJson = prefs.getString("city", "");
//        if (citiesJson != "")
//            return Gson().fromJson(citiesJson, City::class.java);
//        else
//            return
//    }

    fun getCityFromPreferences(): String {
        var prefs = context.getSharedPreferences(CITIES_PREFS_FILENAME, Context.MODE_PRIVATE)
        return prefs.getString("city", "");
    }

    fun setCityInPreferences(city: City) {
        var prefs = context.getSharedPreferences(CITIES_PREFS_FILENAME, Context.MODE_PRIVATE)
        prefs.edit().putString("city", Gson().toJson(city)).apply()
    }
}