package com.example.weatherapp.network

import android.util.Log
import okhttp3.*
import java.io.IOException
import android.R.string
import android.os.AsyncTask.execute
import java.lang.Exception


class BasicClient {

    private val client = OkHttpClient()
    val TAG = "BasicClient";

    fun get(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                return response.body()?.string()
            }
        } catch (e: Exception) {
                return "";
        }
    }

}