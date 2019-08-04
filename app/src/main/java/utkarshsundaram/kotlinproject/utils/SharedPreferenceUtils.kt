package utkarshsundaram.kotlinproject.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.android.gms.common.util.SharedPreferencesUtils

class SharedPreferenceUtils
{
   companion object {
       public val TAG = SharedPreferencesUtils::class.java!!.getSimpleName()
       public val SAMPLE_WEATHER_SHARED_PREF = "SAMPLE_WEATHER_SHARED_PREF"
       public val LATITUDE = "latitude"
       public val LONGITUDE = "longitude"
       fun setLatitude(context: Context, latitude: String) {
           Log.v(TAG, "setLatitude")
           try {
               val sharedPref = context.getSharedPreferences(SAMPLE_WEATHER_SHARED_PREF, Context.MODE_PRIVATE)
               val editor = sharedPref.edit()
               editor.putString(LATITUDE, latitude)
               editor.apply()
           } catch (e: NullPointerException) {
               Log.e(TAG, "error")
           }

       }

       fun setLongitude(context: Context, longitude: String) {
           Log.v(TAG, "setLongitude")
           try {
               val sharedPref = context.getSharedPreferences(SAMPLE_WEATHER_SHARED_PREF, Context.MODE_PRIVATE)
               val editor = sharedPref.edit()
               editor.putString(LONGITUDE, longitude)
               editor.apply()
           } catch (e: NullPointerException) {
               Log.e(TAG, "error")
           }

       }

       /**
        * This method is used to get a User token from fileSystem
        *
        * @param context context
        * @return an token
        */


       fun getLatitude(context: Context): String? {
           Log.v(TAG, "getLatitude")
           try {
               val sharedPref = context.getSharedPreferences(SAMPLE_WEATHER_SHARED_PREF, Context.MODE_PRIVATE)
               return sharedPref.getString(LATITUDE, null)
           } catch (e: NullPointerException) {
               Log.e(TAG, "error")
               return null
           }

       }

       fun getLongitude(context: Context): String? {
           Log.v(TAG, "getLongitude")
           try {
               val sharedPref = context.getSharedPreferences(SAMPLE_WEATHER_SHARED_PREF, Context.MODE_PRIVATE)
               return sharedPref.getString(LONGITUDE, null)
           } catch (e: NullPointerException) {
               Log.e(TAG, "error")
               return null
           }

       }


       fun clearSharedPreference(context: Context) {
           val preferences = context.getSharedPreferences(SAMPLE_WEATHER_SHARED_PREF, Context.MODE_PRIVATE)
           val editor = preferences.edit()
           editor.clear()
           editor.commit()
       }
   }
}