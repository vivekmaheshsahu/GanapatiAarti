package digispark.tech.ganapatiaarti

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.calibehr.mitra.utils.SharedPreferencesHelper
import digispark.tech.ganapatiaarti.base.BaseActivity
import digispark.tech.ganapatiaarti.constants.Constant

import java.util.ArrayList

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils


class Splash_Screen : Activity() {

    internal var handler: Handler? = Handler()
    private val MULTIPLE_PERMISSIONS = 10
    private val permissions = arrayOf(Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    internal var r: Runnable = Runnable {
        if (checkPermissions()) {
            //  permissions  granted.
            determineNavigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        val sp = SharedPreferencesHelper.getPref(this@Splash_Screen, Constant.SP_LANG_CODE)
        if (sp!!.isNotEmpty()){
            Constant.LANG_CODE = sp
        }
    }

    override fun onStart() {
        super.onStart()
        handler?.postDelayed(r, 2000)
    }

    private fun determineNavigation() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }

    private fun checkPermissions(): Boolean {
        var result: Int
        val listPermissionsNeeded = ArrayList<String>()
        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(this, p)
            if (result != PackageManager.PERMISSION_GRANTED)
                listPermissionsNeeded.add(p)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray<String>(), MULTIPLE_PERMISSIONS)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MULTIPLE_PERMISSIONS -> {
                var permissionDenied = false
                for (granted in grantResults) {
                    if (granted == PackageManager.PERMISSION_DENIED) {
                        permissionDenied = true
                        UserInterfaceUtils.showToast(this, getString(R.string.please_grant_all_permissions))
                        break
                    }
                }
                if (!permissionDenied)
                    startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        }
    }

    override fun onBackPressed() {

        super.onBackPressed()
        try {
            finish()
            if (handler != null) {
                handler?.removeMessages(0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
