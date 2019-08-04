package utkarshsundaram.kotlinproject.model

class DayForecast
{
    public var date: String=""
    public var day: CurrentWeatherModel?
        get() = CurrentWeatherModel()
        set(value) = TODO()
}