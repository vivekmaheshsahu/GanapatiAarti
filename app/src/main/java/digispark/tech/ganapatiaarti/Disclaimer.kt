package digispark.tech.ganapatiaarti

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Disclaimer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disclaimer)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
