package com.example.weatherapp.models

class Forecast {
    var currently: Currently
    var hourlyForecast: ArrayList<HourForecast>
    var dayForecast: ArrayList<DayForecast>

    constructor(currently: Currently, hourlyForecast: ArrayList<HourForecast>, dayForecast: ArrayList<DayForecast>) {
        this.currently = currently
        this.hourlyForecast = hourlyForecast
        this.dayForecast = dayForecast
    }

    constructor() {
        this.currently = Currently("", 2.2, 2.2, 0.0, 233)
        this.hourlyForecast = ArrayList()
        this.dayForecast = ArrayList()
    }

    override fun toString(): String {
        return "Forecast(currently=$currently, hourlyForecast=$hourlyForecast, dayForecast=$dayForecast)"
    }
}

class Currently {
    var summary: String
    var currentTemp: Double
    var windSpeed: Double
    var humidity: Double
    var day: Long

    constructor(summary: String, currentTemp: Double, humidity: Double, windSpeed: Double, day: Long) {
        this.summary = summary
        this.currentTemp = currentTemp
        this.windSpeed = windSpeed
        this.humidity = humidity
        this.day = day
    }
}

class HourForecast {
    var summary: String
    var temp: Double
    var windSpeed: Double
    var humidity: Double
    var time: Long
    var icon: String

    constructor(summary: String, temp: Double, windSpeed: Double, humidity: Double, time: Long, icon: String) {
        this.summary = summary
        this.temp = temp
        this.windSpeed = windSpeed
        this.humidity = humidity
        this.time = time
        this.icon = icon
    }
}

class DayForecast {
    var summary: String
    var lowTemp: Double
    var highTemp: Double
    var humidity: Double
    var windSpeed: Double
    var time: Long
    var icon: String

    constructor(
        summary: String,
        lowTemp: Double,
        highTemp: Double,
        humidity: Double,
        windSpeed: Double,
        time: Long,
        icon: String
    ) {
        this.summary = summary
        this.lowTemp = lowTemp
        this.highTemp = highTemp
        this.humidity = humidity

        this.windSpeed = windSpeed
        this.time = time
        this.icon = icon
    }
}

