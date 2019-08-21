package digispark.tech.ganapatiaarti

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class Feedback : AppCompatActivity(), View.OnClickListener {
    private var btn_feedback: Button? = null
    private var etMail_feedback_message: EditText? = null
    private var emailMessege: String? = null
    private val TAG = "FeedbackActivity"
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        title = "FeedBack"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        etMail_feedback_message = findViewById<View>(R.id.etMail_feedback_message) as EditText

        btn_feedback = findViewById<View>(R.id.btn_feedback) as Button
        btn_feedback!!.setOnClickListener(this)

        adView = findViewById<View>(R.id.adView) as AdView
        UserInterfaceUtils.showBannerAd(adView)
    }

    override fun onClick(v: View) {
        if (v === btn_feedback) {
            emailMessege = etMail_feedback_message!!.text.toString()

            if (emailMessege!!.equals("", ignoreCase = true)) {
                Toast.makeText(this, "Please Enter Feedback", Toast.LENGTH_SHORT).show()
            } else {
                sendFeedback()
            }
        }
    }

    private fun sendFeedback() {
        val feedbckIntent = Intent(android.content.Intent.ACTION_SEND)
        feedbckIntent.type = "text/html"
        feedbckIntent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf(getString(R.string.mail_feedback_email)))
        feedbckIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject))
        feedbckIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailMessege)
        startActivity(Intent.createChooser(feedbckIntent, getString(R.string.title_send_feedback)))
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
