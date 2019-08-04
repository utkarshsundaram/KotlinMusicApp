package utkarshsundaram.kotlinproject.application

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utkarshsundaram.kotlinproject.network.ApiClientInterface
import utkarshsundaram.kotlinproject.utils.Constants.BASE_URL
import utkarshsundaram.kotlinproject.utils.Constants.CONNECT_TIME_OUT
import utkarshsundaram.kotlinproject.utils.Constants.READ_TIME_OUT
import java.util.concurrent.TimeUnit

class WeatherApplication: Application()
{
    companion object {
       lateinit var mRetrofit:Retrofit;
        fun getClient(): ApiClientInterface {
            return Companion.mRetrofit.create(ApiClientInterface::class.java!!)
        }
    }

    override fun onCreate() {
        super.onCreate()
        setRetrofitClient();
    }

    public fun setRetrofitClient() {
      val httpLoggingInterceptor :HttpLoggingInterceptor= HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient:OkHttpClient=OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(READ_TIME_OUT,TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
            .build()
           mRetrofit=Retrofit.Builder()
           .client(okHttpClient)
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
               .build()


    }


}