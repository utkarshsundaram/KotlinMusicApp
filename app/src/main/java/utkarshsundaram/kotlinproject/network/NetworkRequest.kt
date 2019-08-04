package utkarshsundaram.kotlinproject.network

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class NetworkRequest<T>:Callback<T> {
    private val TAG = NetworkRequest::class.java.simpleName

    private val mReqType: Int
    private val mContext: Context
    private val mUpdateListener:onUpdateListener
    constructor(
        mReqType: Int,
        mContext: Context,
        mUpdateListener: onUpdateListener
    ) {
        this.mReqType = mReqType
        this.mContext = mContext
        this.mUpdateListener = mUpdateListener
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        TODO("not implemented")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful && response.body() != null) {
            mUpdateListener.onUpdateView(response.body(), true, mReqType)
        } else {
            if (response.code() == 401) {
            } else if (response.code() == 403) {
                //Invalid or expire token
            } else {
                mUpdateListener.onUpdateView(response.message(), false, mReqType)
            }
        }
    }

    interface onUpdateListener {
        fun onUpdateView(response: Any?, success:Boolean, reqType: Int);
    }
}