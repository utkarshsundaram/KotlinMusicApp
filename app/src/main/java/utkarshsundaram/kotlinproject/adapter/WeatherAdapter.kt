package utkarshsundaram.kotlinproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_weather_details.view.*
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.model.Forecast

class WeatherAdapter(var mContext:Context): RecyclerView.Adapter<WeatherAdapter.WeatherDetailsViewHolder>() {
    private var mForecastList=Forecast();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.WeatherDetailsViewHolder {
    return  WeatherDetailsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weather_details,parent,false))
    }
    fun setListItem(mForecastList:Forecast){
        this.mForecastList=mForecastList;
        notifyDataSetChanged()
    };
    override fun getItemCount(): Int
    {
        return mForecastList.forecastday?.size ?:0
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherDetailsViewHolder, position: Int) {
        holder.textDay?.setText(mForecastList.forecastday?.get(position)?.date)
        holder.textTemperature?.setText(mForecastList.forecastday?.get(position)?.day?.avgtemp_c.toString());
    }
class  WeatherDetailsViewHolder(view: View) :RecyclerView.ViewHolder(view){
    val textTemperature=view.textTemp
    val textDay=view.textDay
}
}