package com.sp.loylapclover.weatherapp.db

import com.sp.loylapclover.weatherapp.domain.model.DomainClasses

/**
 * Created by danie on 17/07/2017.
 */
class DbDataMapper {
    fun convertFromDomain(forecast: DomainClasses.ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: DomainClasses.Forecast) =
            with(forecast) {
                DayForecast(date, description, high, low, iconUrl, cityId)
            }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        DomainClasses.ForecastList(_id, city, country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        DomainClasses.Forecast(date, description, high, low, iconUrl)
    }
}