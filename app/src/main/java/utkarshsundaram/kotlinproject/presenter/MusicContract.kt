package utkarshsundaram.kotlinproject.presenter

import utkarshsundaram.kotlinproject.model.MusicModel

interface MusicContract
{
    interface View :BaseView<Presenter>{
        fun onMusicList(musicModel: ArrayList<MusicModel>)
    }

    interface Presenter{
        fun getCurrentMusicList();
    }
}