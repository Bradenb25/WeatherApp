package com.example.weatherapp.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.weatherapp.R
import com.example.weatherapp.adapters.CityListAdapter
import com.example.weatherapp.models.City
import com.example.weatherapp.network.BasicClient
import com.example.weatherapp.utils.CityJsonParser

import kotlinx.android.synthetic.main.activity_city_search.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CitySearch : AppCompatActivity() {


    private lateinit var cities: ArrayList<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_search)
        setSupportActionBar(toolbar)


    }

    fun sendResult() {

    }

    fun search(v: View) {

        var searchBar = findViewById<EditText>(R.id.search_bar_text)
        var searchTerm = searchBar.text;


        var baseClient = BasicClient()

        doAsync {

            var searchResult = baseClient
                .get("http://geodb-free-service.wirefreethought.com/v1/geo/cities?namePrefix=$searchTerm&limit=5&offset=0&hateoasMode=false")

            uiThread {

                cities = (CityJsonParser().parseCitiesJson(searchResult));
                setCityListView(cities);
            }
        }

    }



    fun setCityListView(cities: ArrayList<City>) {
        var cityListRecyclerView = findViewById<RecyclerView>(R.id.city_list_recycler_view)

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        var adapter = CityListAdapter(this, cities);
        cityListRecyclerView.layoutManager = layoutManager;

        cityListRecyclerView.adapter = adapter;
    }
}
