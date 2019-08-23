package digispark.tech.ganapatiaarti

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.ads.AdView

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class pune_mandle_detail : AppCompatActivity() {
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pune_mandle_detail)
        title = "Pune Mandals"
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.loadAd(adView)

        val intent = intent
        val intValue = intent.getIntExtra("songindex", 0)

        val t1: TextView
        val t2: TextView
        val im1: ImageView
        im1 = findViewById<View>(R.id.img1) as ImageView
        t1 = findViewById<View>(R.id.txt) as TextView
        t2 = findViewById<View>(R.id.t1) as TextView
        if (intValue == 0) {
            im1.setImageResource(R.drawable.g23)

            t2.text = "Shrimant Dagdusheth Halwai Ganapati"
            t1.text = "Dagdusheth Halwai Ganesha has a history of more than 100 years and is participating in the Ganeshotsav from 1893"
        }
        if (intValue == 1) {
            im1.setImageResource(R.drawable.g24)
            t2.text = "Rajarshi Shahu Chowk Ganeshotsav Mandal"
            t1.text = "Ganesh mandal in Pune is famous of Hindu temples in India during Ganesh Chaturthi."
        }
        if (intValue == 2) {
            im1.setImageResource(R.drawable.g25)
            t2.text = "Tambadi Jogeshwari Ganapati Mandal"
            t1.text = "This Ganesha Mandal has been participating in Ganeshotsav from 1893 and the Jogeshwari Ganpati is one of the most famous Sarvajanik Ganesha Festival."
        }
        if (intValue == 3) {
            im1.setImageResource(R.drawable.g26)
            t2.text = "Hutatma Babu Genu Ganesh Mandal "
            t1.text = "This Sarvajanik Mandal is noted for its huge and beautiful Ganesh Pandals"
        }
        if (intValue == 4) {
            im1.setImageResource(R.drawable.g27)
            t2.text = "Mandai Ganeshotsav Mandal "
            t1.text = "This popular Sarvajanik Ganesh mandal is noted for its decorations and also for involvement in social causes. It is also runs a cooperative credit society."
        }
        if (intValue == 5) {
            im1.setImageResource(R.drawable.g28)
            t2.text = "Tulshibag Sarvajanik Ganeshotsav "
            t1.text = "This popular Ganesh Mandal at Budhwar Peth attracts thousands of devotees.  14-feet Ganesh idol of Tulshibag is popular among devotees."
        }
        if (intValue == 6) {
            im1.setImageResource(R.drawable.g29)
            t2.text = "Guruji Talim Ganapati "
            t1.text = "Established in 1887, Guruji Talim Ganapati is popular for its decorations and replicas of temples and popular monuments. One of the oldest mandals in Pune city."
        }
        if (intValue == 7) {
            im1.setImageResource(R.drawable.g30)
            t2.text = "Hatti Ganpati Sarvajanik "
            t1.text = "This pandal is famous for its decorations. Pujas and rituals are attended by dedicated devotees every year."
        } else {

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
