package utkarshsundaram.kotlinproject.presenter

import utkarshsundaram.kotlinproject.model.WeatherModel

interface WeatherContract
{
    interface View :BaseView<Presenter>{
        fun onWeatherReport(weatherModel: WeatherModel)
    }

    interface Presenter{
       fun getCurrentWeather();
    }
}