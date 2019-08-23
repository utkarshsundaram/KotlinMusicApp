package utkarshsundaram.kotlinproject.presenter

import utkarshsundaram.kotlinproject.persistance.AppDatabase

class ImageViewPresenter(private val mView: ImageViewContract.View) : ImageViewContract.Presenter {

    override fun deleteItemFromList(loginKeys: String) {
        AppDatabase.instance.musicDao().delete(loginKeys)
        mView.onMusicListDelete()
    }

}