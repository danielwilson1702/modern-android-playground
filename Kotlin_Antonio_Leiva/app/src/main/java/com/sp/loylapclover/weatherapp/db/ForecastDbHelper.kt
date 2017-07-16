package com.sp.loylapclover.weatherapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.sp.loylapclover.weatherapp.db.Tables.CityForecastTable
import com.sp.loylapclover.weatherapp.db.Tables.DayForecastTable
import com.sp.loylapclover.weatherapp.ui.App
import org.jetbrains.anko.db.*

/**
 * Created by Daniel on 16/07/2017.
 */
class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(App.instance,
        ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() } //Not created until used, threadsafe
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}