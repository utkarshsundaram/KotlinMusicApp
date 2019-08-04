package utkarshsundaram.kotlinproject.presenter

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.application.WeatherApplication
import utkarshsundaram.kotlinproject.model.WeatherModel
import utkarshsundaram.kotlinproject.network.ApiClientInterface
import utkarshsundaram.kotlinproject.utils.Constants
import utkarshsundaram.kotlinproject.utils.SharedPreferenceUtils
import java.util.HashMap

class WeatherPresenter : WeatherContract.Presenter {
    private val TAG = WeatherPresenter::class.java.canonicalName
    private val mView: WeatherContract.View
    private val mContext: Context
    private val mNetworkApi: ApiClientInterface

    constructor(mView: WeatherContract.View, mContext: Context ) {
        this.mView = mView
        this.mContext = mContext
        mNetworkApi=WeatherApplication.getClient()
    }

    override fun getCurrentWeather() {
        val location = SharedPreferenceUtils.getLatitude(mContext) + "," + SharedPreferenceUtils.getLongitude(mContext)
        val params = HashMap<String, String>()
        params[Constants.API_PARAMS.WEATHER_PARAM] = location
        params[Constants.API_PARAMS.DAYS_PARAM] = Constants.API_PARAMS.DAYS
        val responseCall = mNetworkApi.getWeatherForecastList(params)
        responseCall.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.body() != null) {
                    var weatherModel:WeatherModel=response.body() as WeatherModel;
                    mView.onWeatherReport(weatherModel)
                    Log.d(TAG, "response=" + response.body()!!)
                } else {
                    mView.onResponseFailure(Exception(mContext.getString(R.string.un_expected_error)))
                }
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                mView.onResponseFailure(t)
                t.printStackTrace()
            }
        })
    }
}