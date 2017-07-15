package com.sp.loylapclover.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sp.loylapclover.weatherapp.domain.model.DomainClasses
import com.sp.loylapclover.weatherapp.extensions.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by Daniel on 15/07/2017.
 */
class ForecastListAdapter(val weekForecast: DomainClasses.ForecastList,
                          val itemClick: OnItemClickListener) :
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

    class ViewHolder(view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view){

        private val iconView: ImageView = view.find(R.id.icon)
        private val dateView: TextView = view.find(R.id.date)
        private val descriptionView: TextView = view.find(R.id.description)
        private val maxTemperatureView: TextView = view.find(R.id.maxTemperature)
        private val minTemperatureView: TextView = view.find(R.id.minTemperature)

        fun bindForecast(forecast: DomainClasses.Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener{ itemClick(this)}
            }
        }
    }

    interface OnItemClickListener {
        operator fun invoke(forecast: DomainClasses.Forecast)
    }

}
