package com.sp.loylapclover.weatherapp.domain.data

import com.google.gson.Gson

/**
 * Created by Daniel on 15/07/2017.
 */
class ForecastRequest(val zipcode: Long) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ResponseClasses.ForecastResult{
        val forecastJsonStr = java.net.URL(COMPLETE_URL + zipcode).readText()
        return Gson().fromJson(forecastJsonStr, ResponseClasses.ForecastResult::class.java)
    }
}