package utkarshsundaram.kotlinproject.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import utkarshsundaram.kotlinproject.model.WeatherModel

interface ApiClientInterface
{
    @GET(ApiInterface.GET_WEATHER)
    abstract fun getWeatherForecastList(@QueryMap(encoded = true) getQuery: Map<String, String>): Call<WeatherModel>
}