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

public class written_aarti_hindi extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumsAdapter_written_hindi adapter;
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
        adapter = new AlbumsAdapter_written_hindi(albumList);

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
                R.drawable.datta,
                R.drawable.album_2,
                R.drawable.img11,
                R.drawable.album11
        };

        Album a = new Album("सुखकर्ता दुखहर्ता", 13, covers[0]);
        albumList.add(a);

        a = new Album("शेंदूर लाल चढायो", 8, covers[1]);
        albumList.add(a);

        a = new Album("लवथवती विक्राळा", 12, covers[2]);
        albumList.add(a);

        a = new Album("दुर्गे दुर्घट भारी", 14, covers[3]);
        albumList.add(a);

        a = new Album("युगें अठ्ठावीस", 1, covers[4]);
        albumList.add(a);

        a = new Album("येई हो विठ्ठले माझे माऊली ये", 11, covers[5]);
        albumList.add(a);

        a = new Album("जय गणेश जय गणेश देवा", 11, covers[6]);
        albumList.add(a);

        a = new Album("ओम जय जगदीश हरे", 17, covers[7]);
        albumList.add(a);
        a = new Album("श्रीज्ञानदेवाची आरती ज्ञानराजा", 17, covers[8]);
        albumList.add(a);
        a = new Album("घालीन लोटांगण", 17, covers[9]);
        albumList.add(a);
        a = new Album("श्री दत्त आरती", 17, covers[10]);
        albumList.add(a);
        a = new Album("जय अम्बे गौरी मैया जय श्यामा गौरी", 17, covers[11]);
        albumList.add(a);
        a = new Album("प्रार्थना", 17, covers[12]);
        albumList.add(a);
        a = new Album("पपुष्पांजली", 17, covers[13]);
        albumList.add(a);
        adapter.notifyDataSetChanged();
    }


    public boolean onOptionsItemSelected(MenuItem item)

    {
        Intent intent = new Intent(written_aarti_hindi.this, MainActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(written_aarti_hindi.this, MainActivity.class);
        startActivity(intent);
    }


}

