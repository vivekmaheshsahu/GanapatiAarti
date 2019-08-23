package digispark.tech.ganapatiaarti.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import digispark.tech.ganapatiaarti.base.BaseContextWrapper;
import digispark.tech.ganapatiaarti.constants.Constant;

public class DeviceDetails {

    public static void setLocale(String langCode) {
        try {
            Locale locale = new Locale(langCode);
            Locale.setDefault(locale);

            Resources resources = Constant.getAPP_CONTEXT().getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            android.content.res.Configuration conf = resources.getConfiguration();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                conf.setLocale(locale);
            } else {
                conf.locale = locale;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Constant.getAPP_CONTEXT().createConfigurationContext(conf);
            } else {
                resources.updateConfiguration(conf, dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", "exception " + e.getMessage());
        }
    }

    public static void setLocaleForApiBelow24() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            setLocale(Constant.getLANG_CODE());
        }
    }

    public static ContextWrapper setLocaleForApi24AndAbove(Context context) {
        Locale locale = new Locale(Constant.getLANG_CODE());
        Locale.setDefault(locale);
        return BaseContextWrapper.wrap(context, locale);
    }
}
