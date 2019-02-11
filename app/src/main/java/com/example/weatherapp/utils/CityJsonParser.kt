package com.example.weatherapp.utils

import com.example.weatherapp.models.City
import org.json.JSONObject

class CityJsonParser {


    fun parseCitiesJson(json: String?): List<City> {

        var cities = ArrayList<City>();

        if (json != null) {

            var raw = JSONObject(json);
            var jsonCities = raw.getJSONArray("data");
            var asdf = jsonCities[0]

            for (i in 0..(jsonCities.length() - 1)) {
                cities.add(parseCityJson(jsonCities.getJSONObject(i)))
            }
        }
        return cities;
    }


    fun parseCityJson(json: JSONObject): City {
        var cityName = json.getString("city");
        var region = json.getString("region");
        var country = json.getString("country");

        var latitude = json.getDouble("latitude")
        var longitude = json.getDouble("longitude");

        return City(cityName, region, country, latitude, longitude);
    }

}