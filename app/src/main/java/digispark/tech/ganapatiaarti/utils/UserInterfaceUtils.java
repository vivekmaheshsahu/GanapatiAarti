package digispark.tech.ganapatiaarti.utils;

import android.content.Context;
import android.widget.Toast;

public class UserInterfaceUtils {
    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
