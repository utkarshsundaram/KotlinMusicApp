package utkarshsundaram.kotlinproject.utils

object Constants
{
    const val ALL_PERMISSIONS_RESULT=100;
    const val UPDATE_REQUEST:Long=85000;
    const val FASTEST_REQUEST:Long=15000;
    const val READ_TIME_OUT:Long=5000
    const val CONNECT_TIME_OUT:Long=1000
    const val BASE_URL = "https://api.github.com"
    const val SPLASH_TIME_OUT: Long = 2000;

    interface INTENT_KEYS {
        companion object {
            val IMAGE_KEYS = "ImagePath"
            val USER_NAME_KEY = "USER_NAME"
            val IMAGE_VIEW: Int = 10;
        }
    }
}