package digispark.tech.ganapatiaarti;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class Mumbai_mandle extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumsAdapter_mandal adapter;
    private List<Album> albumList;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.g1,R.drawable.g5,R.drawable.g9,R.drawable.g11,R.drawable.g14,R.drawable.g19,R.drawable.g18};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mumbai_mandle);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Mumbai Mandals");
        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter_mandal(albumList);

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

        Album a = new Album("GSB Seva Kings Circle", 13, covers[0]);
        albumList.add(a);

        a = new Album("Lalbaugcha Raja", 8, covers[1]);
        albumList.add(a);

        a = new Album("Ganesh Galli Mumbaicha Raja", 11, covers[2]);
        albumList.add(a);

        a = new Album("Chinchpoklicha Chintamani", 12, covers[3]);
        albumList.add(a);

        a = new Album("Chembur Raja", 14, covers[4]);
        albumList.add(a);

        a = new Album("Khetwadi Ganraj", 1, covers[5]);
        albumList.add(a);

        a = new Album("Andhericha Raja", 11, covers[8]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Mumbai_mandle.this,XMENArray));
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


