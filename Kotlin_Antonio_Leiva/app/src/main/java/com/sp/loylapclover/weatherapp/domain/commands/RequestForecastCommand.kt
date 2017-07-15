package com.sp.loylapclover.weatherapp.domain.commands

import com.sp.loylapclover.weatherapp.domain.data.ForecastRequest
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses
import com.sp.loylapclover.weatherapp.domain.ForecastDataMapper

/**
 * Created by Daniel on 15/07/2017.
 */
class RequestForecastCommand(val zipCode: String) : Command<DomainClasses.ForecastList> {
    override fun execute(): DomainClasses.ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}