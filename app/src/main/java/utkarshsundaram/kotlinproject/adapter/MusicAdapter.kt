package utkarshsundaram.kotlinproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_weather_details.view.*
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.listener.OnClickListener
import utkarshsundaram.kotlinproject.model.MusicModel

class MusicAdapter(var mContext: Context, var onClickListener: OnClickListener) :
    RecyclerView.Adapter<MusicAdapter.MusicDetailsViewHolder>() {
    private var mMusicList = ArrayList<MusicModel>();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.MusicDetailsViewHolder {
        return MusicDetailsViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_weather_details,
                parent,
                false
            )
        )
    }

    fun setListItem(musicModel: ArrayList<MusicModel>) {
        this.mMusicList = musicModel;
        notifyDataSetChanged()
    };
    override fun getItemCount(): Int
    {
        return mMusicList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MusicAdapter.MusicDetailsViewHolder, position: Int) {
        holder.textType?.setText(mMusicList.get(position)?.type)
        holder.textName?.setText(mMusicList.get(position)?.login);
        Picasso.get().load(mMusicList.get(position)?.avatar_url).into(holder.imageView)
        holder.layout.setOnClickListener {
            onClickListener.onClick(
                mMusicList.get(position).login,
                mMusicList.get(position).avatar_url
            )
        }

    }

    class MusicDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName = view.textName
        val textType = view.textType
        val imageView = view.imageView
        val layout = view.layout
    }

}