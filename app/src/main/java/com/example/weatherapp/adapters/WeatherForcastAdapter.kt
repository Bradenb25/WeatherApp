package com.example.weatherapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.models.DayForecast
import com.example.weatherapp.models.Weather
import java.text.SimpleDateFormat
import java.util.*

class DayForecastAdapter(val context: Context, val dayForecastList: ArrayList<DayForecast>)
    : RecyclerView.Adapter<DayForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.weather_day, parent, false)
        return DayForecastAdapter.ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.highTemp.text = dayForecastList[position].highTemp.toInt().toString()
        holder.lowTemp.text = dayForecastList[position].lowTemp.toInt().toString()
        var date = Date(dayForecastList[position].time * 1000)
        var time = SimpleDateFormat("EEEE")
            .format(date)
        holder.day.text = time;

        var icon: String = dayForecastList[position].icon.replace('-', '_')

        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "$icon", "drawable",
            context.packageName
        )
        holder.weatherPic.setImageResource(resourceId)
    }

    override fun getItemCount(): Int {
        return dayForecastList.size.toInt()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var day = itemView.findViewById(R.id.day) as TextView
        var weatherPic = itemView.findViewById(R.id.weather_pic) as ImageView
        var highTemp = itemView.findViewById(R.id.high_temp) as TextView
        var lowTemp = itemView.findViewById(R.id.low_temp) as TextView
    }
}