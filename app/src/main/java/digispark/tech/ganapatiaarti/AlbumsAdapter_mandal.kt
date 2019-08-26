package digispark.tech.ganapatiaarti

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import digispark.tech.ganapatiaarti.activities.MandalDetailActivity


class AlbumsAdapter_mandal(private val albumList: List<Album>) : RecyclerView.Adapter<AlbumsAdapter_mandal.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView


        init {
            itemView.setOnClickListener(this)
            this.title = itemView.findViewById<View>(R.id.title) as TextView

        }


        override fun onClick(itemview: View) {
            val intent = Intent(itemview.context, MandalDetailActivity::class.java)
            intent.putExtra("songindex", adapterPosition)
            itemview.context.startActivity(intent)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.mumbai_mandle_view, parent, false)

        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        val textViewName = holder.title


        textViewName.text = albumList[listPosition].name

    }


    override fun getItemCount(): Int {
        return albumList.size
    }


}