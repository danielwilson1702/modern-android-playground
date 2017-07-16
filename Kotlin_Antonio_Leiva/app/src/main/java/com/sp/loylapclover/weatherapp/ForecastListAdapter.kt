package com.sp.loylapclover.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses
import com.sp.loylapclover.weatherapp.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by Daniel on 15/07/2017.
 */
class ForecastListAdapter(val weekForecast: DomainClasses.ForecastList,
                          val itemClick: (DomainClasses.Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
                .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(view: View, val itemClick: (DomainClasses.Forecast) -> Unit) : RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: DomainClasses.Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener{ itemClick(this)}
            }
        }
    }
}
