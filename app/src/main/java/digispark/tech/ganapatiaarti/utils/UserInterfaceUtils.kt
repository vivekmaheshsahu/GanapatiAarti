package digispark.tech.ganapatiaarti.utils

import android.content.Context
import android.widget.Toast

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd

import digispark.tech.ganapatiaarti.R

object UserInterfaceUtils {
    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showBannerAd(adView: AdView?) {
        if (adView != null) {
            val adRequest = AdRequest.Builder()
                    //.addTestDevice("FD9F133038F995D8A876271BC9EBFCC0")  to test the ads on raj device(samsung j7) when run app from android studio
                    .build()
            adView.loadAd(adRequest)
        }
    }

    fun showInterstitialAd(context: Context?) {
        if (context != null) {
            val adRequest = AdRequest.Builder()
                    //.addTestDevice("FD9F133038F995D8A876271BC9EBFCC0") to test the ads on raj device(samsung j7) when run app from android studio
                    .build()
            val interstitialAd = InterstitialAd(context)
            interstitialAd.adUnitId = context.resources.getString(R.string.interstitial_ad)
            interstitialAd.loadAd(adRequest)
        }
    }
}
