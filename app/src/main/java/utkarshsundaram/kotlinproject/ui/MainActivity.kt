package utkarshsundaram.kotlinproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.adapter.MusicAdapter
import utkarshsundaram.kotlinproject.listener.OnClickListener
import utkarshsundaram.kotlinproject.model.MusicModel
import utkarshsundaram.kotlinproject.persistance.AppDatabase
import utkarshsundaram.kotlinproject.presenter.MusicContract
import utkarshsundaram.kotlinproject.presenter.MusicPresenter
import utkarshsundaram.kotlinproject.utils.Constants.INTENT_KEYS.Companion.IMAGE_KEYS
import utkarshsundaram.kotlinproject.utils.Constants.INTENT_KEYS.Companion.IMAGE_VIEW
import utkarshsundaram.kotlinproject.utils.Constants.INTENT_KEYS.Companion.USER_NAME_KEY

class MainActivity : AppCompatActivity(), MusicContract.View, OnClickListener {


    lateinit var musicPresenter: MusicContract.Presenter
    var musicAdapter: MusicAdapter = MusicAdapter(this, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.adapter = musicAdapter
        setPresenter(MusicPresenter(this, this))
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        musicAdapter.setListItem(AppDatabase.instance.musicDao().getAll() as ArrayList<MusicModel>)
        musicAdapter.notifyDataSetChanged()
    }

    override fun onMusicList(musicModel: ArrayList<MusicModel>) {
        musicAdapter.setListItem(AppDatabase.instance.musicDao().getAll() as ArrayList<MusicModel>)
        musicAdapter.notifyDataSetChanged()

    }

    override fun setPresenter(presenter: MusicContract.Presenter) {
        musicPresenter = presenter;
        musicPresenter.getCurrentMusicList()

    }

    override fun onClick(userId: String?, imagePath: String?) {
        val intent = Intent(this, ImageViewActivity::class.java)
        intent.putExtra(USER_NAME_KEY, userId)
        intent.putExtra(IMAGE_KEYS, imagePath)
        startActivityForResult(intent, IMAGE_VIEW)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onResponseFailure(ex: Throwable) {

    }


}

