package com.sp.loylapclover.weatherapp.domain

import com.sp.loylapclover.weatherapp.domain.data.ResponseClasses
import com.sp.loylapclover.weatherapp.domain.data.ResponseClasses.ForecastResult
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses.ForecastList
import java.text.DateFormat
import java.util.*
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses.Forecast as ModelForecast

/**
 * Created by Daniel on 15/07/2017.
 */
class ForecastDataMapper {
    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult) = with(forecast) {
                ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<ResponseClasses.Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ResponseClasses.Forecast) = with(forecast) {
                ModelForecast(dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
                                generateIconUrl(weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}