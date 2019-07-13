package digispark.tech.ganapatiaarti;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AlbumsAdapter_mandal_pune extends RecyclerView.Adapter<AlbumsAdapter_mandal_pune.MyViewHolder> {


    private List<Album> albumList;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.title = (TextView) itemView.findViewById(R.id.title);

        }


        public void onClick(View itemview) {
            int a;
            a=getPosition();
            Intent intent = new Intent(itemview.getContext(), pune_mandle_detail.class);
            intent.putExtra("songindex",a);
            itemview.getContext().startActivity(intent);

        }
    }


    public AlbumsAdapter_mandal_pune(List<Album> albumList) {
        this.albumList = albumList;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mumbai_mandle_view, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.title;


        textViewName.setText(albumList.get(listPosition).getName());

    }




    public int getItemCount() {
        return albumList.size();
    }



}