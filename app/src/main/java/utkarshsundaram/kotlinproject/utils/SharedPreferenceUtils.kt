package utkarshsundaram.kotlinproject.utils

import android.content.Context
import com.google.android.gms.common.util.SharedPreferencesUtils

class SharedPreferenceUtils
{
   companion object {
       public val TAG = SharedPreferencesUtils::class.java!!.getSimpleName()
       public val SAMPLE_MUSIC_SHARED_PREF = "SAMPLE_MUSIC_SHARED_PREF"
       fun clearSharedPreference(context: Context) {
           val preferences = context.getSharedPreferences(SAMPLE_MUSIC_SHARED_PREF, Context.MODE_PRIVATE)
           val editor = preferences.edit()
           editor.clear()
           editor.commit()
       }
   }
}