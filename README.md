# WeatherApp
A simple weather app to display the current temperature as well as a 24 hour forecast and a 24 hour Forecast.

This was a simple project I did to try out Kotlin along with a few RecyclerViews and custom Adapters.
In addition this uses two APIs to access both GPS coordinates and weather information based on the GPS
coordinates.

To get the project up and running you need to do the following things:
  1. Go to https://darksky.net/dev/register and register for a free developer API key
  2. Go to the models folder in the project and create a class called APIKeys and insert the following
     code:
     package com.example.weatherapp.models

      object APIKeys {
        var WEATHER_API_KEY = "API_KEY"
      }
  3. You should then be able to build and launch the project on an emulator or actual Android device.


Credits for the API's go to: 
https://darksky.net Weather API
http://geodb-cities-api.wirefreethought.com/ Location API
