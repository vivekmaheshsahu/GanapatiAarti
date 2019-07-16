package digispark.tech.ganapatiaarti;

import android.os.Bundle;
import android.os.Handler;import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils;
import me.relex.circleindicator.CircleIndicator;

public class pune_mandle extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AlbumsAdapter_mandal_pune adapter;
    private List<Album> albumList;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.g23,R.drawable.g27,R.drawable.g25,R.drawable.g26,R.drawable.g24,R.drawable.g28,R.drawable.g29,R.drawable.g30};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pune_mandle);
        setTitle("Pune Mandals");

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adView = findViewById(R.id.adView);
        UserInterfaceUtils.showBannerAd(adView);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter_mandal_pune(albumList);

        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(this);
        mlayoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutmanager);
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        init();

    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album3,
                R.drawable.album2,
                R.drawable.album1,
                R.drawable.album4,
                R.drawable.album_2,
                R.drawable.album6,
                R.drawable.album5,
                R.drawable.album1,
                R.drawable.img11,
                R.drawable.album7
        };

        Album a = new Album("Shrimant Dagdusheth Halwai Ganapati", 13, covers[0]);
        albumList.add(a);

        a = new Album("Rajarshi Shahu Chowk Ganeshotsav Mandal", 8, covers[1]);
        albumList.add(a);

        a = new Album("Tambadi Jogeshwari Ganapati Mandal", 11, covers[2]);
        albumList.add(a);

        a = new Album("Hutatma Babu Genu Ganesh Mandal ", 12, covers[3]);
        albumList.add(a);

        a = new Album("Mandai Ganeshotsav Mandal ", 14, covers[4]);
        albumList.add(a);

        a = new Album("Tulshibag Sarvajanik Ganeshotsav ", 1, covers[5]);
        albumList.add(a);

        a = new Album("Guruji Talim Ganapati ", 11, covers[8]);
        albumList.add(a);

        a = new Album("Hatti Ganpati Sarvajanik ", 11, covers[8]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(pune_mandle.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


