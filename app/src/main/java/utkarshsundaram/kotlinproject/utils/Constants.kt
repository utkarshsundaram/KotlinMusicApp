package utkarshsundaram.kotlinproject.utils

object Constants
{
    const val ALL_PERMISSIONS_RESULT=100;
    const val UPDATE_REQUEST:Long=85000;
    const val FASTEST_REQUEST:Long=15000;
    const val READ_TIME_OUT:Long=5000
    const val CONNECT_TIME_OUT:Long=1000
    const val BASE_URL="http://api.apixu.com/v1/"

    interface API_PARAMS {
        companion object {
            val WEATHER_PARAM = "q"
            val DAYS_PARAM = "days"
            val DAYS = "4"
        }
    }
}