package digispark.tech.ganapatiaarti;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AlbumsAdapter_written_hindi extends RecyclerView.Adapter<AlbumsAdapter_written_hindi.MyViewHolder> {


    private List<Album> albumList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }


        public void onClick(View itemview) {
            int a;
            a=getPosition();
            Intent intent = new Intent(itemview.getContext(), written_hindi_detail_aarti.class);
            intent.putExtra("songindex",a);
            itemview.getContext().startActivity(intent);

        }
    }


    public AlbumsAdapter_written_hindi(List<Album> albumList) {
        this.albumList = albumList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.writen_aarti_english, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.title;
        ImageView imageView = holder.thumbnail;

        textViewName.setText(albumList.get(listPosition).getName());
        imageView.setImageResource(albumList.get(listPosition).getThumbnail());
    }




    public int getItemCount() {
        return albumList.size();
    }



}