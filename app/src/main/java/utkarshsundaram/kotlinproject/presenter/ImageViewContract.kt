package utkarshsundaram.kotlinproject.presenter

interface ImageViewContract {
    interface View : BaseView<Presenter> {
        fun onMusicListDelete()
    }

    interface Presenter {
        fun deleteItemFromList(loginKeys: String);
    }
}