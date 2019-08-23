package utkarshsundaram.kotlinproject.network

import retrofit2.Call
import retrofit2.http.GET
import utkarshsundaram.kotlinproject.model.MusicList

interface ApiClientInterface
{
    @GET(ApiInterface.USERS)
    abstract fun getMusicModel(): Call<MusicList>
}