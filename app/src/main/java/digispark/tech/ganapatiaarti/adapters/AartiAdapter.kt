package digispark.tech.ganapatiaarti.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import digispark.tech.ganapatiaarti.R
import digispark.tech.ganapatiaarti.ShowAartiActivity
import digispark.tech.ganapatiaarti.pojo.AlbumNew

class AartiAdapter(private val albumList: ArrayList<AlbumNew>) : RecyclerView.Adapter<AartiAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView
        var thumbnail: ImageView

        init {
            itemView.setOnClickListener(this)
            this.title = itemView.findViewById<View>(R.id.title) as TextView
            this.thumbnail = itemView.findViewById<View>(R.id.thumbnail) as ImageView
        }


        override fun onClick(itemview: View) {
            val a: Int
            a = adapterPosition
            val intent = Intent(itemview.context, ShowAartiActivity::class.java)
            intent.putExtra("aarti_name", albumList[a].name)
            intent.putExtra("aarti_text", albumList[a].raw)
            intent.putExtra("aartiIndex", a)
            intent.putParcelableArrayListExtra("listOfAartis", albumList)
            itemview.context.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view_aarti, parent, false)

        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, listPosition: Int) {
        val textViewName = holder.title
        val imageView = holder.thumbnail

        textViewName.setText(albumList[listPosition].name)
        imageView.setImageResource(albumList[listPosition].thumbnail)
    }


    override fun getItemCount(): Int {
        return albumList.size
    }


}
