package digispark.tech.ganapatiaarti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils;

public class Written_aarti_english extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AlbumsAdapter_written_english adapter;
    private List<Album> albumList;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_written_aarti_english);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adView = findViewById(R.id.adView);
        UserInterfaceUtils.showBannerAd(adView);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter_written_english(albumList);

        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(this);
        mlayoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutmanager);
        recyclerView.setAdapter(adapter);
        prepareAlbums();


    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album3,
                R.drawable.album2,
                R.drawable.album4,
                R.drawable.album_2,
                R.drawable.album6,
                R.drawable.album5,
                R.drawable.img11,
                R.drawable.album7,
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album_2,
                R.drawable.datta,
                R.drawable.album11
        };

        Album a = new Album("Sukhkarta Dukhharta", 13, covers[0]);
        albumList.add(a);

        a = new Album("Shendur Lal Chadhayo", 8, covers[1]);
        albumList.add(a);

        a = new Album("Lavthavti Vikrala", 12, covers[2]);
        albumList.add(a);

        a = new Album("Durge Durghat Bhari", 14, covers[3]);
        albumList.add(a);

        a = new Album("Yuge Atthavis", 1, covers[4]);
        albumList.add(a);

        a = new Album("Yei Oh Vitthale", 11, covers[5]);
        albumList.add(a);


        a = new Album("Jai Ganesh Jai Ganesh", 11, covers[6]);
        albumList.add(a);

        a = new Album("Om Jai Jagdish Hare", 17, covers[7]);
        albumList.add(a);

        a = new Album("Shri Dynarajachi Aarti", 17, covers[8]);
        albumList.add(a);

        a = new Album("Ghalin lotangan vandin charan", 17, covers[9]);
        albumList.add(a);
        a = new Album("Jai ambe gauri maiya jai shyama gauri", 17, covers[10]);
        albumList.add(a);
        a = new Album("Shri Dattachi Aarti", 17, covers[11]);
        albumList.add(a);
        a = new Album("Mantra pushpanjali", 11, covers[12]);
        albumList.add(a);
        adapter.notifyDataSetChanged();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Written_aarti_english.this, MainActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
   public void onBackPressed()
   {
       Intent intent = new Intent(Written_aarti_english.this, MainActivity.class);
       startActivity(intent);
   }


}

