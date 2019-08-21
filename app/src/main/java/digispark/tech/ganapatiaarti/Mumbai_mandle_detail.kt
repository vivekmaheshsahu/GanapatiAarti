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

class Mumbai_mandle_detail : AppCompatActivity() {
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mumbai_mandle_detail)
        title = "Mumbai Mandals"
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.showBannerAd(adView)

        val intent = intent
        val intValue = intent.getIntExtra("songindex", 0)

        val t1: TextView
        val t2: TextView
        val im1: ImageView
        val im2: ImageView
        val im3: ImageView
        im1 = findViewById<View>(R.id.img1) as ImageView
        im2 = findViewById<View>(R.id.img2) as ImageView
        im3 = findViewById<View>(R.id.img3) as ImageView
        t1 = findViewById<View>(R.id.txt) as TextView
        t2 = findViewById<View>(R.id.t1) as TextView
        if (intValue == 0) {
            im1.setImageResource(R.drawable.g1)
            im2.setImageResource(R.drawable.g2)
            im3.setImageResource(R.drawable.g3)

            t2.text = "GSB Seva Kings Circle"
            t1.text = "The GSB Seva Ganesh mandal is affectionately known as Mumbai's gold Ganesh. Yes, that's pure gold it's adorned with -- more than 60 kilograms of it! The idol is always an eco-friendly one, made out of clay. The mandal is also distinctive because there's none of the usual recorded music there. Instead, traditional Indian musical instruments used in south Indian temples are played.\n\n\n" +
                    "•\tLocation: G.S.B. Sports Club Ground, Near S.N.D.T. Women's College, R.A. Kidwai Road, King's Circle, Matunga (central Mumbai).\n\n" +
                    "•\tNearest Railway Station: Kings Circle on the Harbour Line and Matunga on the Central Line.\n"
        }
        if (intValue == 1) {
            im1.setImageResource(R.drawable.g4)
            im2.setImageResource(R.drawable.g5)
            im3.setImageResource(R.drawable.g6)
            t2.text = "Lalbaugcha Raja"
            t1.text = "Lalbaugcha Raja, King of Lalbaug, is undoubtedly the most famous Ganesh statue in Mumbai. On average of 1.5 million people visit a day. People believe that this Ganesh idol can fulfill their wishes, and there's a lot of media attention on it. Lot of people wishes get complete here.\n\n\n" +
                    "•\tLocation: Lalbaug market, GD Ambedkar Road, Lalbaug (central Mumbai).\n\n" +
                    "•\tNearest Railway Station: Walking distance from Lower Parel, Curry Road, and Chinchpolki stations.\n\n"
        }
        if (intValue == 2) {
            im1.setImageResource(R.drawable.g7)
            im2.setImageResource(R.drawable.g8)
            im3.setImageResource(R.drawable.g9)
            t2.text = "Ganesh Galli Mumbaicha Raja"
            t1.text = "The Mumbaicha Raja, in Ganesh Galli (Lane), is located near Lalbaugh. The mandal is well known for its new themes every year, often a replica of a famous place in India. It was formed for the benefit of the mill workers in 1928, making it the oldest one in the area.\n\n\n" +
                    "•\tLocation: Ganesh Galli (Lane), Lalbaug (central Mumbai).\n\n" +
                    "•\tNearest Railway Station: Chinchpokli, Curry Road, and Lower Parel railway stations are close by.\n\n"
        }
        if (intValue == 3) {
            im1.setImageResource(R.drawable.g10)
            im2.setImageResource(R.drawable.g11)
            im3.setImageResource(R.drawable.g12)
            t2.text = "Chinchpoklicha Chintamani"
            t1.text = "Chinchpoklicha Chintamani, this is among the city’s oldest. \n\n" +
                    "\n" +
                    "•\tLocation: Near Chinchpokli Station\n\n" +
                    "Nearest Railway Station: Chinchpokli, Curry Road, and Lower Parel railway stations are close by\n\n"
        }
        if (intValue == 5) {
            im1.setImageResource(R.drawable.g13)
            im2.setImageResource(R.drawable.g14)
            im3.setImageResource(R.drawable.g15)
            t2.text = "Khetwadi Ganraj"
            t1.text = "The award-winning Khetwadi Ganraj is considered to be one of the most spectacular Ganesh idols in Mumbai. The idol is decked out in real gold jewelry. An added attraction when visiting the Khetwadi Ganraj is that there's a Ganesh idol in almost every lane in the area\n\n\n" +
                    "•\tLocation: 12th Lane Kehetwadi, Girgaum (south Mumbai).\n\n" +
                    "•\tNearest Railway Station: The nearest stations are Charni Road and Sandhurst Road.\n\n"
        }
        if (intValue == 6) {
            im1.setImageResource(R.drawable.g16)
            im2.setImageResource(R.drawable.g17)
            im3.setImageResource(R.drawable.g18)
            t2.text = "Andhericha Raja"
            t1.text = "The Andhericha Raja is to the Mumbai suburbs what the Lalbaugcha Raja is to south Mumbai. The idol isn't as towering or imposing. However, it has a reputation for fulfilling wishes. The mandal also usually has a novel theme and other attractions\n\n\n" +
                    "•\tLocation: Veera Desai Road, Azad Nagar, Andheri West (western Mumbai suburbs).\n\n" +
                    "•\tNearest Railway Station: Andheri.\n"
        }
        if (intValue == 4) {
            im1.setImageResource(R.drawable.g19)
            im2.setImageResource(R.drawable.g20)
            im3.setImageResource(R.drawable.g22)
            t2.text = "Chembur Raja"
            t1.text = "Chembur ka Raja is famous idol in chembur camp. Idol is more than 16 feet tall and 14 feet wide of a traditional sitting Ganesha blessing his followers has been kept unchanged for long time.There are Ganesh idol in almost every lane in the area\n\n\n" +
                    "•\tLocation: Sindhi Camp Chembur\n\n" +
                    "•\tNearest Railway Station: Chembur Station \n"
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
