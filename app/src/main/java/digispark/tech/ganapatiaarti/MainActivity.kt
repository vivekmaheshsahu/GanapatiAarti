package digispark.tech.ganapatiaarti

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics

import java.util.ArrayList

import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.firebase.NotificationHelper
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var recyclerView: RecyclerView? = null
    private var adapter: AlbumsAdapter? = null
    private var albumList: MutableList<Album>? = null
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var adView: AdView? = null
    private var mTracker: Tracker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtain the FirebaseAnalytics instance.
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        val application = application as MyApplication
        mTracker = application.defaultTracker

        Log.i("MainActivity", "MainActivity screen name: ")
        mTracker!!.setScreenName("Activity Name " + "Main Activity")
        mTracker!!.send(HitBuilders.ScreenViewBuilder().build())

        mTracker!!.send(HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build())

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.showBannerAd(adView)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId = getString(R.string.interstitial_ad)
        mInterstitialAd!!.loadAd(AdRequest.Builder().build())
        mInterstitialAd!!.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad")
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened")
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication")
            }

            override fun onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed")
                // load next ad
                mInterstitialAd!!.loadAd(AdRequest.Builder().build())
            }
        }

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView

        albumList = ArrayList()
        adapter = AlbumsAdapter(albumList!!)

        val mLayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter

        prepareAlbums()

    }

    override fun onResume() {
        super.onResume()

        NotificationHelper.clearNotifications(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else {
            if (mInterstitialAd!!.isLoaded)
                mInterstitialAd!!.show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.hindi_aarti) {
            val intent = Intent(this@MainActivity, written_aarti_hindi::class.java)
            startActivity(intent)
            // Handle the camera action
        } else if (id == R.id.English_aarti) {
            val intent = Intent(this@MainActivity, Written_aarti_english::class.java)
            startActivity(intent)

        } else if (id == R.id.mumbai_mandle) {
            val intent = Intent(this@MainActivity, Mumbai_mandle::class.java)
            startActivity(intent)
        } else if (id == R.id.pune) {
            val intent = Intent(this@MainActivity, pune_mandle::class.java)
            startActivity(intent)
        } else if (id == R.id.Disclaimer) {
            val intent = Intent(this@MainActivity, Disclaimer::class.java)
            startActivity(intent)
        } else if (id == R.id.share) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, Constant.PLAY_STORE_LINK)

            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        } else if (id == R.id.feedback) {
            val intent = Intent(this@MainActivity, Feedback::class.java)
            startActivity(intent)
        } else if (id == R.id.update) {

            val uri = Uri.parse(Constant.PLAY_STORE_LINK)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)

            // After pressing back button from google play will continue to app
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK)

            startActivity(goToMarket)
        } else if (id == R.id.exit) {
            if (MusicPlayerActivity.mp != null) {
                MusicPlayerActivity.mp!!.stop()
                MusicPlayerActivity.mp!!.release()
                MusicPlayerActivity.mp = null
            }
            val exitIntent = Intent(Intent.ACTION_MAIN)
            exitIntent.addCategory(Intent.CATEGORY_HOME)
            exitIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(exitIntent)
            finish()
        }


        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album1, R.drawable.album2, R.drawable.album3, R.drawable.album4, R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.album1, R.drawable.img11, R.drawable.album7)

        var a = Album("ekadantaya_vakratundaya", 13, covers[0])
        albumList!!.add(a)

        a = Album("Sindoor Lal Chadayo", 8, covers[1])
        albumList!!.add(a)

        a = Album("Sukhkarta Dukhharta", 11, covers[2])
        albumList!!.add(a)

        a = Album("Lavthavti Vikrala", 12, covers[3])
        albumList!!.add(a)

        a = Album("Durge Durghat Bhari", 14, covers[4])
        albumList!!.add(a)

        a = Album("Yuge Atthavis", 1, covers[5])
        albumList!!.add(a)

        a = Album("Yei Oh Vitthale", 11, covers[6])
        albumList!!.add(a)

        a = Album("Tu Sukhkarta Tu Dukhharta", 14, covers[7])
        albumList!!.add(a)

        a = Album("Jai Ganesh Jai Ganesh", 11, covers[8])
        albumList!!.add(a)

        a = Album("Om Jai Jagdish Hare", 17, covers[9])
        albumList!!.add(a)

        adapter!!.notifyDataSetChanged()
    }


    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }


}
