package com.example.weatherapp.models

class Weather {
    var lowTemp: Int
    var highTemp: Int
    var humidity: Int
    var currentTemp: Int
    var day: String

    constructor(lowTemp: Double, highTemp: Double, humidity: Int, currentTemp: Double, day: String) {
        this.lowTemp = lowTemp.kelvinToFarenheit()
        this.highTemp = highTemp.kelvinToFarenheit()
        this.humidity = humidity
        this.currentTemp = currentTemp.kelvinToFarenheit()
        this.day = day
    }

    fun Double.kelvinToCelsius() : Int {
        return (this - 273.15).toInt()
    }

    fun Double.kelvinToFarenheit(): Int {
        return ((this - 273.15) * 9/5 + 32).toInt()
    }

    override fun toString(): String {
        return "Weather(lowTemp=$lowTemp, highTemp=$highTemp, humidity=$humidity, currentTemp=$currentTemp)"
    }


}