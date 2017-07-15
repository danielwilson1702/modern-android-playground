package com.sp.loylapclover.weatherapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sp.loylapclover.weatherapp.domain.commands.RequestForecastCommand
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forecastList: RecyclerView = find(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        async {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                longToast("Request performed")
                forecastList.adapter = ForecastListAdapter(result,
                        object: ForecastListAdapter.OnItemClickListener{
                            override fun invoke(forecast: DomainClasses.Forecast) {
                                toast(forecast.date)
                            }
                        })
            }
        }
    }
}
