package com.example.weatherapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.models.City

class CityListAdapter(val context: Context, val cities: ArrayList<City>):
    RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.city_name_view, parent, false)
        return CityListAdapter.ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return cities.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityView.text = getCityName(cities[position]);
    }

    fun getCityName(city: City): String {
        var location = "${city.cityName}, ${city.region} ${city.country}"
        return location
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var cityView = itemView.findViewById<TextView>(R.id.city_name)
    }
}