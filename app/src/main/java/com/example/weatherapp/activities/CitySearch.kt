package com.example.weatherapp.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.weatherapp.R
import com.example.weatherapp.network.BasicClient
import com.example.weatherapp.utils.CityJsonParser

import kotlinx.android.synthetic.main.activity_city_search.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CitySearch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)
        setSupportActionBar(toolbar)

        search("Rexburg");
    }

    fun search(searchTerm: String) {
        var baseClient = BasicClient()

        var searchResult = baseClient.get("http://geodb-free-service.wirefreethought.com/v1/geo/cities?namePrefix=$searchTerm&limit=5&offset=0&hateoasMode=false")

        doAsync {

            var cities = (CityJsonParser().parseCitiesJson(searchResult));
            uiThread {

                var a = 3;
            }
        }

    }

}
