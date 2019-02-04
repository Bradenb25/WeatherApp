package com.example.weatherapp.adapters

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.models.HourForecast
import java.text.SimpleDateFormat
import java.util.*



class HourForecastAdapter(val context: Context, val dayForecastList: ArrayList<HourForecast>)
    : RecyclerView.Adapter<HourForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourForecastAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.forecast_layout, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return dayForecastList.size
    }

    override fun onBindViewHolder(holder: HourForecastAdapter.ViewHolder, position: Int) {
        holder.forecastTemp.text = dayForecastList[position].temp.toInt().toString()
        var date = Date(dayForecastList[position].time * 1000)
        var time = SimpleDateFormat("HH")
            .format(date)
        holder.time.text = getTime(time)

        var icon: String = dayForecastList[position].icon.replace('-', '_')

        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "$icon", "drawable",
            context.packageName
        )

        holder.weatherIcon.setImageResource(resourceId)
    }


    fun getTime(time: String): String {
        var formattedTime = time.toInt() % 12

        if (time == "00")
            return "12 am"

        return when (formattedTime) {
            time.toInt() -> "$formattedTime am"
            0 -> "12 pm"
            else -> "$formattedTime pm"
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time = itemView.findViewById(R.id.day_forecast_time) as TextView
        var forecastTemp = itemView.findViewById(R.id.forecast_temp) as TextView
        var weatherIcon = itemView.findViewById(R.id.weather_icon) as ImageView
    }

}