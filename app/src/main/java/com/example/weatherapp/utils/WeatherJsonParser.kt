package com.example.weatherapp.utils

import com.example.weatherapp.models.*
import org.json.JSONObject

class WeatherJsonParser {


//    fun parseCurrentWeather(weatherJSON: String?): Weather {
//        if (weatherJSON != null) {
//            val root = JSONObject(weatherJSON);
//
//            val mainWeather = root.getJSONObject("main");
//            val currentTemp = mainWeather.getDouble("temp")
//            val humidity = mainWeather.getInt("humidity")
//            val lowTemp = mainWeather.getDouble("temp_min");
//            val highTemp = mainWeather.getDouble("temp_max");
////            val
//
//            return Weather(lowTemp, highTemp, humidity, currentTemp, "Monday");
//        } else {
//            return Weather(0.0,0.0, 0, 0.0, "Monday")
//        }
//    }

    fun parseCurrentWeather(weatherJSON: String?): Forecast {
        if (weatherJSON != null) {
            val root = JSONObject(weatherJSON);

            val currently = root.getJSONObject("currently");

            val currentWeather: Currently =
                Currently(
                    currently.getString("summary"),
                    currently.getDouble("temperature"),
                    currently.getDouble("humidity"),
                    currently.getDouble("windSpeed"),
                    currently.getLong("time")

                )

            val hourRoot = root.getJSONObject("hourly")
            val hourArray = hourRoot.getJSONArray("data")

            var hourForecastList: ArrayList<HourForecast> = ArrayList()

            for (i in 0..(hourArray.length() - 1)) {

                val hourForecastRoot = hourArray.getJSONObject(i);

                var summary: String = hourForecastRoot.getString("summary")
                var temp: Double = hourForecastRoot.getDouble("temperature")
                var windSpeed: Double = hourForecastRoot.getDouble("windSpeed")
                var humidity: Double = hourForecastRoot.getDouble("humidity")
                var time: Long = hourForecastRoot.getLong("time")
                var icon: String = hourForecastRoot.getString("icon")

                var hourForecast = HourForecast(summary, temp, windSpeed, humidity, time, icon)
                hourForecastList.add(hourForecast)
            }

            var dayForecastList = ArrayList<DayForecast>()

            var dailyRoot = root.getJSONObject("daily");
            var dailyArray = dailyRoot.getJSONArray("data");

            for (i in 0..(dailyArray.length() - 1)) {

                var day = dailyArray.getJSONObject(i)
                var summary: String = day.getString("summary")
                var lowTemp: Double = day.getDouble("temperatureLow")
                var highTemp: Double = day.getDouble("temperatureHigh")
                var humidity: Double = day.getDouble("humidity")
                var windSpeed: Double = day.getDouble("windSpeed")
                var time: Long = day.getLong("time")
                var icon: String = day.getString("icon");

                var dayForecast = DayForecast(summary, lowTemp, highTemp, humidity, windSpeed, time, icon)
                dayForecastList.add(dayForecast)
            }

            return Forecast(currentWeather, hourForecastList, dayForecastList);
        } else {
            return Forecast()
        }
    }

}