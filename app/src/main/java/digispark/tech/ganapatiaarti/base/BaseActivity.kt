package digispark.tech.ganapatiaarti.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import digispark.tech.ganapatiaarti.utils.DeviceDetails

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // keep the Application window size FULL and display ON
        super.onCreate(savedInstanceState)
        DeviceDetails.setLocaleForApiBelow24()
        setContentView(provideLayoutId())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(DeviceDetails.setLocaleForApi24AndAbove(newBase))
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int
}