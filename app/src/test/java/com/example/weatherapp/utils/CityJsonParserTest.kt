package com.example.weatherapp.utils

import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CityJsonParserTest {

    @Test
    fun parseCityJson() {
        val cityJsonParser = CityJsonParser()
        val cityJson = "{\"id\":140041,\"type\":\"ADM2\",\"city\":\"Los Angeles County\",\"country\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"California\",\"regionCode\":\"CA\",\"latitude\":34.19801,\"longitude\":-118.26102}"
        val jsonObject = JSONObject(cityJson)
        val city = cityJsonParser.parseCityJson(jsonObject)

        assertEquals("Los Angeles County", city.cityName)
        assertEquals("California", city.region)
        assertEquals("United States of America", city.country)
        assertEquals(34.19801, (city.latitude), .00001)
        assertEquals(-118.26102, city.longitude, .00001)
    }

    @Test
    fun parseCityJsonList() {
        val cityJsonParser = CityJsonParser()
        val cityJson = "{\"data\":[{\"id\":115061,\"wikiDataId\":\"Q65\",\"type\":\"CITY\",\"city\":\"Los Angeles\",\"country\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"California\",\"regionCode\":\"CA\",\"latitude\":34.05223,\"longitude\":-118.24368},{\"id\":115061,\"wikiDataId\":\"Q65\",\"type\":\"CITY\",\"city\":\"Los Angeles\",\"country\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"California\",\"regionCode\":\"CA\",\"latitude\":34.05223,\"longitude\":-118.24368},{\"id\":115061,\"wikiDataId\":\"Q65\",\"type\":\"CITY\",\"city\":\"Los Angeles\",\"country\":\"United States of America\",\"countryCode\":\"US\",\"region\":\"California\",\"regionCode\":\"CA\",\"latitude\":34.05223,\"longitude\":-118.24368}],\"metadata\":{\"currentOffset\":0,\"totalCount\":3}}"
        val cities = cityJsonParser.parseCitiesJson(cityJson)

        for (i in 0..(cities.size - 1)) {
            val city = cities[i]
            assertEquals("Los Angeles", city.cityName)
            assertEquals("California", city.region)
            assertEquals("United States of America", city.country)
            assertEquals(34.05223, (city.latitude), .00001)
            assertEquals(-118.24368, city.longitude, .00001)
        }
    }
}