package digispark.tech.ganapatiaarti

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class MusicAdapter(private val mCtx: Context?, private val albumList: ArrayList<MusicPojo>,
                   private val progressBarInterface: ProgressBarInterface) :
        RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {

    internal var handler = Handler()
    private var songIndex: Int? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView
        var thumbnail: ImageView

        init {
            itemView.setOnClickListener(this)
            this.title = itemView.findViewById<View>(R.id.title) as TextView
            this.thumbnail = itemView.findViewById<View>(R.id.thumbnail) as ImageView
        }


        override fun onClick(itemview: View) {
            progressBarInterface.showProgressBar()
            if (MusicPlayerActivity.mp != null) {
                MusicPlayerActivity.mp?.stop()
                MusicPlayerActivity.mp = null
            }
            songIndex = adapterPosition
            Log.d("test", "position_index $adapterPosition")
            Log.d("Test", "SongInfo_name " + albumList[adapterPosition].name)
            handler.postDelayed(r, 900)
        }
    }

    internal var r: Runnable = Runnable {
        progressBarInterface.hideProgressBar()
        val intent = Intent(mCtx, MusicPlayerActivity::class.java)
        intent.putExtra("songindex", songIndex)
        mCtx?.startActivity(intent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.music_list_item, parent, false)

        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        val textViewName = holder.title
        val imageView = holder.thumbnail

        textViewName.text = albumList[listPosition].name
        imageView.setImageResource(albumList[listPosition].thumbnail)
    }


    override fun getItemCount(): Int {
        return albumList.size
    }

    interface ProgressBarInterface{
        fun showProgressBar()
        fun hideProgressBar()
    }

}