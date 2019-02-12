package com.example.weatherapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.utils.SharedPreferencesHelper

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var cityJson = SharedPreferencesHelper(this).getCityFromPreferences();
        if (cityJson != "") {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("city", cityJson)
            startActivity(intent);
        } else {
            var intent = Intent(this, CityList::class.java)
            startActivity(intent);
        }
    }
}
