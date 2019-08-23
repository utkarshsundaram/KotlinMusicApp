package utkarshsundaram.kotlinproject.presenter

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.application.MusicApplication
import utkarshsundaram.kotlinproject.model.MusicList
import utkarshsundaram.kotlinproject.network.ApiClientInterface
import utkarshsundaram.kotlinproject.persistance.AppDatabase

class MusicPresenter : MusicContract.Presenter {
    private val TAG = MusicPresenter::class.java.canonicalName
    private val mView: MusicContract.View
    private val mContext: Context
    private val mNetworkApi: ApiClientInterface

    constructor(mView: MusicContract.View, mContext: Context) {
        this.mView = mView
        this.mContext = mContext
        mNetworkApi = MusicApplication.getClient()
    }

    override fun getCurrentMusicList() {
        val responseCall = mNetworkApi.getMusicModel()
        responseCall.enqueue(object : Callback<MusicList> {
            override fun onResponse(call: Call<MusicList>, response: Response<MusicList>) {
                if (response.body() != null) {
                    var musicModel: MusicList = response.body() as MusicList;
                    for (item in musicModel.musicList!!) {
                        AppDatabase.instance.musicDao().insert(item)
                    }
                    musicModel.musicList?.let { mView.onMusicList(it) }
                    Log.d(TAG, "response=" + response.body()!!)
                } else {
                    mView.onResponseFailure(Exception(mContext.getString(R.string.un_expected_error)))
                }
            }

            override fun onFailure(call: Call<MusicList>, t: Throwable) {
                mView.onResponseFailure(t)
                t.printStackTrace()
            }
        })
    }
}