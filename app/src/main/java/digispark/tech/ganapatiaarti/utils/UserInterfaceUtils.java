package digispark.tech.ganapatiaarti.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import digispark.tech.ganapatiaarti.R;

public class UserInterfaceUtils {
    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showBannerAd(AdView adView){
        if (adView != null){
            AdRequest adRequest = new AdRequest.Builder()
                    //.addTestDevice("FD9F133038F995D8A876271BC9EBFCC0")  to test the ads on raj device(samsung j7) when run app from android studio
                    .build();
            adView.loadAd(adRequest);
        }
    }

    public static void showInterstitialAd(Context context){
        if (context != null){
            AdRequest adRequest = new AdRequest.Builder()
                    //.addTestDevice("FD9F133038F995D8A876271BC9EBFCC0") to test the ads on raj device(samsung j7) when run app from android studio
                    .build();
            InterstitialAd interstitialAd = new InterstitialAd(context);
            interstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial_ad));
            interstitialAd.loadAd(adRequest);
        }
    }
}
