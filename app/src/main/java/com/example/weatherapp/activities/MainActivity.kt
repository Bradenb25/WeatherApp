package com.example.weatherapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.DayForecastAdapter
import com.example.weatherapp.adapters.HourForecastAdapter
import com.example.weatherapp.models.APIKeys
import com.example.weatherapp.models.Currently
import com.example.weatherapp.models.DayForecast
import com.example.weatherapp.models.HourForecast
import com.example.weatherapp.network.BasicClient
import com.example.weatherapp.utils.WeatherJsonParser
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, CitySearch::class.java);
        startActivity(intent)

//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
        requestLocationPermissions()

//        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null)
                updateLocation(location.latitude, location.longitude);
            else
                updateLocation(44.3, -111.25);
        }
    }

    private fun updateLocation(lat: Double, long: Double) {
        val basicClient = BasicClient();

        doAsync {
            var json =
                basicClient.get("https://api.darksky.net/forecast/${APIKeys.WEATHER_API_KEY}/$lat,$long");
            uiThread {
                val weatherParser = WeatherJsonParser()
                var currentWeather = weatherParser.parseCurrentWeather(json)
                Log.d("MainActivity", currentWeather.toString());
                setCurrentWeather(
                    currentWeather.currently,
                    currentWeather.dayForecast[0].highTemp.toInt(),
                    currentWeather.dayForecast[0].lowTemp.toInt()
                )
                setHourRecyclerView(currentWeather.hourlyForecast)
                setDayRecyclerView(currentWeather.dayForecast)
            }
        }
    }

    fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            this,
            Array(1) { android.Manifest.permission.ACCESS_COARSE_LOCATION },
            1
        )
    }

    fun setCurrentWeather(current: Currently, highTemp: Int, lowTemp: Int) {
        var cityView = findViewById(R.id.city) as TextView;
        cityView.text = "Rexburg"

        var currentTempView = findViewById(R.id.current_temp) as TextView;
        currentTempView.text = current.currentTemp.toInt().toString()

        var currentWeatherSummary = findViewById(R.id.simple_current_weather) as TextView;
        currentWeatherSummary.text = current.summary

        var currentDayText = findViewById(R.id.current_day_name) as TextView;
        var day = Date(current.day * 1000)
        currentDayText.text = SimpleDateFormat("EEEE").format(day)

        var highTempView = findViewById(R.id.high_temp) as TextView;
        highTempView.text = highTemp.toString()

        var lowTempView = findViewById(R.id.low_temp) as TextView;
        lowTempView.text = lowTemp.toString()
    }

    fun setHourRecyclerView(hourForecastList: ArrayList<HourForecast>) {

        var hourForecast = (hourForecastList.chunked(24));
        var hr = hourForecast[0] as ArrayList

        var forecastAdapter = HourForecastAdapter(this, hr);

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        var recyclerView: RecyclerView = findViewById(R.id.hour_recycler_view);
        recyclerView.layoutManager = layoutManager;

        recyclerView.adapter = forecastAdapter;
    }

    fun setDayRecyclerView(dayForecastList: ArrayList<DayForecast>) {

        var forecastAdapter = DayForecastAdapter(this, dayForecastList);

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        var dayRecyclerView: RecyclerView = findViewById(R.id.day_forecast_list_view)
        dayRecyclerView.layoutManager = layoutManager

        dayRecyclerView.adapter = forecastAdapter
    }

    fun asyncExample() {
//        launch(CommonPool) {
//            val result = URL("google.com")
//            async(context = UI) {
//                Log.d("Request, result")
//
//            }
//        }
    }
}
