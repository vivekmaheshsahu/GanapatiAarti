package digispark.tech.ganapatiaarti

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.calibehr.mitra.utils.SharedPreferencesHelper
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import com.google.android.material.navigation.NavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import digispark.tech.ganapatiaarti.base.BaseActivity
import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.firebase.NotificationHelper
import digispark.tech.ganapatiaarti.fragments.*
import digispark.tech.ganapatiaarti.utils.DeviceDetails


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    internal val TAG = MainActivity::class.java.simpleName
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mInterstitialAd: InterstitialAd? = null

    companion object {
        lateinit var analytics: GoogleAnalytics
        lateinit var tracker: Tracker
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        analytics = GoogleAnalytics.getInstance(this)
        /*analytics.setLocalDispatchPeriod(1800)
        tracker = analytics.newTracker("UA-88365539-1")
        tracker.enableExceptionReporting(true)
        tracker.enableAdvertisingIdCollection(true)
        tracker.enableAutoActivityTracking(true)*/


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = getString(R.string.interstitial_ad)
        loadAd()
        mInterstitialAd?.adListener = object : AdListener() {
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
                loadAd()
            }
        }

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.itemIconTintList = null
        navigationView.setCheckedItem(R.id.navMusic)
        displaySelectedScreen(R.id.navMusic)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val english = menu?.getItem(1)
        val marathi = menu?.getItem(2)
        val hindi = menu?.getItem(3)
        if (Constant.LANG_CODE.equals("en")){
            english?.setChecked(true)
        }else if (Constant.LANG_CODE.equals("mr")){
            marathi?.setChecked(true)
        }else if (Constant.LANG_CODE.equals("hi")){
            hindi?.setChecked(true)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_english ->{
                if (item.isChecked())
                    item.setChecked(false)
                else
                    item.setChecked(true)

                Constant.LANG_CODE = "en"
                SharedPreferencesHelper.addPref(this@MainActivity, Constant.SP_LANG_CODE, Constant.LANG_CODE)
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
            R.id.action_marathi -> {
                if (item.isChecked())
                    item.setChecked(false)
                else
                    item.setChecked(true)

                Constant.LANG_CODE = "mr"
                SharedPreferencesHelper.addPref(this@MainActivity, Constant.SP_LANG_CODE, Constant.LANG_CODE)
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
            R.id.action_hindi -> {
                if (item.isChecked())
                    item.setChecked(false)
                else
                    item.setChecked(true)

                Constant.LANG_CODE = "hi"
                SharedPreferencesHelper.addPref(this@MainActivity, Constant.SP_LANG_CODE, Constant.LANG_CODE)
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        if (MusicPlayerActivity.mp != null) {
            MusicPlayerActivity.mp?.stop()
            MusicPlayerActivity.mp?.release()
            MusicPlayerActivity.mp = null
        }
        Constant.NOW_PLAYING_SONG_NAME = ""

        return true

    }

    fun loadAd(){
        val adRequest = AdRequest.Builder()
                //.addTestDevice("FD9F133038F995D8A876271BC9EBFCC0")
                .build()
        mInterstitialAd?.loadAd(adRequest)
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
            if (mInterstitialAd?.isLoaded!!)
                mInterstitialAd?.show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displaySelectedScreen(item.itemId)
        return true
    }

    private fun displaySelectedScreen(itemId: Int) {
        var fragment: Fragment? = null

        when (itemId) {
            R.id.navMusic ->{
                supportActionBar?.title = applicationContext.resources.getString(R.string.listen_bhajan)
                fragment = HomeFragment()
            }

            R.id.navHinAartiEng -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.hindi_aarti)
                if (Constant.LANG_CODE.equals("en")){
                    Constant.LANGUAGE = "hindi"
                }
                else
                    Constant.LANGUAGE = "हिंदी"

                fragment = AartiFragment()
            }

            R.id.navMarAartiEng -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.marathi_aarti)
                if (Constant.LANG_CODE.equals("en")){
                    Constant.LANGUAGE = "marathi"
                }
                else
                    Constant.LANGUAGE = "मराठी"
                fragment = AartiFragment()
            }

            R.id.navMumbaiMandle -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.mumbai_mandal)
                fragment = MandalFragment()
            }

            R.id.navPuneMandle -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.pune_mandal)
                fragment = MandalFragment()
            }

            R.id.navOurApps -> {
                goToPlayStore(Constant.OUR_APPS_LINK)
            }

            R.id.navShare -> {
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(Intent.EXTRA_TEXT, Constant.PLAY_STORE_LINK)
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }

            R.id.navFeedback -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.feedback_or_suggestions)
                fragment = FeedbackFragment()
            }

            R.id.navUpdate -> {
                goToPlayStore(Constant.PLAY_STORE_LINK)
            }

            R.id.navExit -> {
                if (MusicPlayerActivity.mp != null) {
                    MusicPlayerActivity.mp?.stop()
                    MusicPlayerActivity.mp?.release()
                    MusicPlayerActivity.mp = null
                }
                Constant.NOW_PLAYING_SONG_NAME = ""
                val exitIntent = Intent(Intent.ACTION_MAIN)
                exitIntent.addCategory(Intent.CATEGORY_HOME)
                exitIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(exitIntent)
                finish()
            }

            R.id.navRateUs ->{
                goToPlayStore(Constant.PLAY_STORE_LINK)
            }

            R.id.navDisclaimer -> {
                supportActionBar?.title = applicationContext.resources.getString(R.string.disclaimer)
                fragment = DisclaimerFragment()
            }
        }
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_home, fragment)
            ft.commit()
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    fun goToPlayStore(strLink: String){
        val uri = Uri.parse(strLink)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // After pressing back button from google play will continue to app
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(goToMarket)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
