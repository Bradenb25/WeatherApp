package com.example.weatherapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.weatherapp.R
import com.example.weatherapp.adapters.CityListAdapter
import com.example.weatherapp.models.ActivityResultCodes
import com.example.weatherapp.models.City
import com.example.weatherapp.models.Constants
import com.example.weatherapp.models.Constants.CITIES_PREFS_FILENAME
import com.example.weatherapp.utils.SharedPreferencesHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_city_list.*



class CityList : AppCompatActivity() {

    private lateinit var cities: ArrayList<City>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
        setSupportActionBar(toolbar)

        updateCityList()

    }

    private fun updateCityList() {
        retreiveCities()
        setCityList()
    }


    private fun setCityList() {
        var cityListRecyclerView = findViewById<RecyclerView>(R.id.city_list_recyclerview)

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        var adapter = CityListAdapter(this, cities);
        cityListRecyclerView.layoutManager = layoutManager;

        cityListRecyclerView.adapter = adapter;
    }

    fun openSearch(view: View) {
        var intent = Intent(this, CitySearch::class.java)
        startActivityForResult(intent, ActivityResultCodes.CITY_REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == ActivityResultCodes.CITY_REQUEST_CODE &&
            resultCode == ActivityResultCodes.RESULT_OK && data != null) {
            var cityJson = data.getStringExtra("city");
            savePreferences(Gson().fromJson(cityJson, City::class.java));
            updateCityList()
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun retreiveCities() {

        var prefs = this.getSharedPreferences(CITIES_PREFS_FILENAME, Context.MODE_PRIVATE)

        var citiesJson = prefs.getString("cities", "");
        cities = if (citiesJson != "") {
            val listType = object : TypeToken<ArrayList<City>>() {}.type
            Gson().fromJson(citiesJson, listType);
        } else {
            ArrayList<City>()
        }
    }

    fun savePreferences(city: City) {
        var prefs = this.getSharedPreferences(CITIES_PREFS_FILENAME, Context.MODE_PRIVATE)
        var citiesJson = prefs.getString("cities", "");

        var cities = if (citiesJson != "") {

            val listType = object : TypeToken<ArrayList<City>>() {}.type
            Gson().fromJson(citiesJson, listType);
        } else {
            ArrayList<City>()
        }

        cities.add(city);
        prefs.edit().putString("cities", Gson().toJson(cities)).apply()
    }
}
