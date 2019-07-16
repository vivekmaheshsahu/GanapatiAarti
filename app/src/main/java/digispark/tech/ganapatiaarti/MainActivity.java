package digispark.tech.ganapatiaarti;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import digispark.tech.ganapatiaarti.constants.Constant;
import digispark.tech.ganapatiaarti.firebase.NotificationHelper;
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        adView = findViewById(R.id.adView);
        UserInterfaceUtils.showBannerAd(adView);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
                // load next ad
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

    }

    @Override
    protected void onResume() {
        super.onResume();

        NotificationHelper.clearNotifications(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
        {
            if (mInterstitialAd.isLoaded())
                mInterstitialAd.show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.hindi_aarti) {
            Intent intent = new Intent(MainActivity.this,written_aarti_hindi.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.English_aarti) {
            Intent intent = new Intent(MainActivity.this,Written_aarti_english.class);
            startActivity(intent);

        }  else if (id == R.id.mumbai_mandle) {
          Intent intent = new Intent(MainActivity.this,Mumbai_mandle.class);
            startActivity(intent);
        }else if (id == R.id.pune) {
            Intent intent = new Intent(MainActivity.this,pune_mandle.class);
            startActivity(intent);
        }else if(id == R.id.Disclaimer)
        {
          Intent intent = new Intent(MainActivity.this,Disclaimer.class);
            startActivity(intent);
        }

        else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, Constant.PLAY_STORE_LINK);

            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if (id == R.id.feedback) {
            Intent intent = new Intent(MainActivity.this, Feedback.class);
            startActivity(intent);
        }
        else if (id == R.id.update) {

            Uri uri = Uri.parse(Constant.PLAY_STORE_LINK);
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);

            // After pressing back button from google play will continue to app
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

            startActivity(goToMarket);
        }
        else if (id == R.id.exit){
            if (MusicPlayerActivity.mp != null){
                MusicPlayerActivity.mp.stop();
                MusicPlayerActivity.mp.release();
                MusicPlayerActivity.mp = null;
            }
            Intent exitIntent = new Intent(Intent.ACTION_MAIN);
            exitIntent.addCategory(Intent.CATEGORY_HOME);
            exitIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exitIntent);
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album_2,
                R.drawable.album6,
                R.drawable.album5,
                R.drawable.album1,
                R.drawable.img11,
                R.drawable.album7
                };

        Album a = new Album("ekadantaya_vakratundaya", 13, covers[0]);
        albumList.add(a);

        a = new Album("Sindoor Lal Chadayo", 8, covers[1]);
        albumList.add(a);

        a = new Album("Sukhkarta Dukhharta", 11, covers[2]);
        albumList.add(a);

        a = new Album("Lavthavti Vikrala", 12, covers[3]);
        albumList.add(a);

        a = new Album("Durge Durghat Bhari", 14, covers[4]);
        albumList.add(a);

        a = new Album("Yuge Atthavis", 1, covers[5]);
        albumList.add(a);

        a = new Album("Yei Oh Vitthale", 11, covers[6]);
        albumList.add(a);

        a = new Album("Tu Sukhkarta Tu Dukhharta", 14, covers[7]);
        albumList.add(a);

        a = new Album("Jai Ganesh Jai Ganesh", 11, covers[8]);
        albumList.add(a);

        a = new Album("Om Jai Jagdish Hare", 17, covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }


public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}