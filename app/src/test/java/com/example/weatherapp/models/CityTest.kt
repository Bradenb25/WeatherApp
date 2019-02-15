package com.example.weatherapp.models

import org.junit.Test

import org.junit.Assert.*

class CityTest {

    @Test
    fun getCityName() {
        val city = City("Hereford", "", "", 1.2, 1.2)

        assertEquals("Hereford", city.cityName)
    }
}