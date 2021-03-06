package com.sp.loylapclover.weatherapp.domain.model

/**
 * Created by Daniel on 15/07/2017.
 */
class DomainClasses {
    data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>){
        operator fun get(position: Int): Forecast = dailyForecast[position]
        fun size(): Int = dailyForecast.size
    }

    data class Forecast(val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)
}