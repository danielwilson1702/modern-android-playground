package com.sp.loylapclover.weatherapp.db

import android.database.sqlite.SQLiteDatabase
import com.sp.loylapclover.weatherapp.db.Tables.*
import com.sp.loylapclover.weatherapp.domain.ForecastDataMapper
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses
import com.sp.loylapclover.weatherapp.extensions.parseList
import com.sp.loylapclover.weatherapp.extensions.parseOpt
import com.sp.loylapclover.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by Daniel on 16/07/2017.
 */
class ForecastDb(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND " +
                "${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: DomainClasses.ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)){
            insert(com.sp.loylapclover.weatherapp.db.Tables.CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(com.sp.loylapclover.weatherapp.db.Tables.DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }
}