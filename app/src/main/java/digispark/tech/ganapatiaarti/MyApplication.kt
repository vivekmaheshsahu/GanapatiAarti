package digispark.tech.ganapatiaarti

import android.app.Application

import com.google.android.gms.ads.MobileAds
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker

class MyApplication : Application() {

    /**
     * Gets the default [Tracker] for this [Application].
     * @return tracker
     */
    // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
    val defaultTracker: Tracker
        @Synchronized get() {
            if (sTracker == null) {
                sTracker = sAnalytics!!.newTracker(R.xml.global_tracker)
            }

            return sTracker!!
        }

    override fun onCreate() {
        super.onCreate()

        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        sAnalytics = GoogleAnalytics.getInstance(this)
    }

    companion object {
        private var sAnalytics: GoogleAnalytics? = null
        private var sTracker: Tracker? = null
    }
}
