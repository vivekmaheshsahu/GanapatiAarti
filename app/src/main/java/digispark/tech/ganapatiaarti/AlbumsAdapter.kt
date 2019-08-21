package digispark.tech.ganapatiaarti

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class AlbumsAdapter(private val albumList: List<Album>) : RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView
        var thumbnail: ImageView

        init {
            itemView.setOnClickListener(this)
            this.title = itemView.findViewById<View>(digispark.tech.ganapatiaarti.R.id.title) as TextView
            this.thumbnail = itemView.findViewById<View>(digispark.tech.ganapatiaarti.R.id.thumbnail) as ImageView
        }


        override fun onClick(itemview: View) {
            val a: Int
            a = adapterPosition
            val intent = Intent(itemview.context, MusicPlayerActivity::class.java)
            intent.putExtra("songindex", a)
            itemview.context.startActivity(intent)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_album, parent, false)

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


}