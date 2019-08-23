package utkarshsundaram.kotlinproject.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_view.*
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.presenter.ImageViewContract
import utkarshsundaram.kotlinproject.presenter.ImageViewPresenter
import utkarshsundaram.kotlinproject.utils.Constants.INTENT_KEYS.Companion.IMAGE_KEYS
import utkarshsundaram.kotlinproject.utils.Constants.INTENT_KEYS.Companion.USER_NAME_KEY

class ImageViewActivity : AppCompatActivity(), View.OnClickListener, ImageViewContract.View {
    lateinit var imageViewPresenter: ImageViewContract.Presenter;
    override fun onClick(v: View?) {

        imageViewPresenter.deleteItemFromList(intent.getStringExtra(USER_NAME_KEY))
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(ImageViewPresenter(this))
        setContentView(R.layout.activity_image_view)
        Picasso.get().load(intent.getStringExtra(IMAGE_KEYS)).into(myZoomageView)
        buttonDelete.setOnClickListener(this)

    }

    override fun setPresenter(presenter: ImageViewContract.Presenter) {
        imageViewPresenter = presenter
    }

    override fun onResponseFailure(ex: Throwable) {
    }

    override fun onMusicListDelete() {
        setResult(Activity.RESULT_OK)
        finish()

    }
}
