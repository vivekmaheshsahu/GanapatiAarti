package digispark.tech.ganapatiaarti

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import digispark.tech.ganapatiaarti.constants.Constant

class MyApplication : Application() {

    val defaultTracker: Tracker
        @Synchronized get() {
            if (sTracker == null) {
                sTracker = sAnalytics!!.newTracker(R.xml.global_tracker)
            }

            return sTracker!!
        }

    override fun onCreate() {
        super.onCreate()
        Constant.APP_CONTEXT = this
        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id))

        sAnalytics = GoogleAnalytics.getInstance(this)
    }

    companion object {
        private var sAnalytics: GoogleAnalytics? = null
        private var sTracker: Tracker? = null

    }
}
