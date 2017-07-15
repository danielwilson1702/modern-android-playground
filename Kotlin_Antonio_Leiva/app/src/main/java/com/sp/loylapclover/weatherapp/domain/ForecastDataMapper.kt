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
    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<ResponseClasses.Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ResponseClasses.Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}