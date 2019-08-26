package digispark.tech.ganapatiaarti.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.ads.AdView
import digispark.tech.ganapatiaarti.R
import digispark.tech.ganapatiaarti.base.BaseActivity
import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class MandalDetailActivity : BaseActivity() {

    private var adView: AdView? = null
    private var tvDetail: TextView? = null
    private var imgMandal: ImageView? = null

    override fun provideLayoutId(): Int {
        return R.layout.activity_mandal_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initializer()
    }

    fun initializer(){
        adView = findViewById(R.id.adView)
        UserInterfaceUtils.loadAd(adView)
        tvDetail = findViewById(R.id.tvDetail)
        imgMandal = findViewById(R.id.imgMandal)

        val intent = intent
        val intValue = intent.getIntExtra("songindex", 0)

        if (Constant.LANGUAGE.equals("mumbai")){
            showMumbaiMandalDetails(intValue)
        }else if (Constant.LANGUAGE.equals("pune")){
            showPuneMandalDetails(intValue)
        }
    }

    fun showMumbaiMandalDetails(position: Int){
        when(position){
            0 -> {
                imgMandal?.setImageResource(R.drawable.g1)
                supportActionBar?.title = resources.getString(R.string.gsb_seva_kings_circle)
                tvDetail?.text = "The GSB Seva Ganesh mandal is affectionately known as Mumbai's gold Ganesh. Yes, that's pure gold it's adorned with -- more than 60 kilograms of it! The idol is always an eco-friendly one, made out of clay. The mandal is also distinctive because there's none of the usual recorded music there. Instead, traditional Indian musical instruments used in south Indian temples are played.\n\n\n" +
                        "•\tLocation: G.S.B. Sports Club Ground, Near S.N.D.T. Women's College, R.A. Kidwai Road, King's Circle, Matunga (central Mumbai).\n\n" +
                        "•\tNearest Railway Station: Kings Circle on the Harbour Line and Matunga on the Central Line.\n"
            }
            1 -> {
                imgMandal?.setImageResource(R.drawable.g4)
                supportActionBar?.title = resources.getString(R.string.lalbaugcha_raja)
                tvDetail?.text = "Lalbaugcha Raja, King of Lalbaug, is undoubtedly the most famous Ganesh statue in Mumbai. On average of 1.5 million people visit a day. People believe that this Ganesh idol can fulfill their wishes, and there's a lot of media attention on it. Lot of people wishes get complete here.\n\n\n" +
                        "•\tLocation: Lalbaug market, GD Ambedkar Road, Lalbaug (central Mumbai).\n\n" +
                        "•\tNearest Railway Station: Walking distance from Lower Parel, Curry Road, and Chinchpolki stations.\n\n"
            }
            2 -> {
                imgMandal?.setImageResource(R.drawable.g7)
                supportActionBar?.title = resources.getString(R.string.ganesh_galli)
                tvDetail?.text = "The Mumbaicha Raja, in Ganesh Galli (Lane), is located near Lalbaugh. The mandal is well known for its new themes every year, often a replica of a famous place in India. It was formed for the benefit of the mill workers in 1928, making it the oldest one in the area.\n\n\n" +
                        "•\tLocation: Ganesh Galli (Lane), Lalbaug (central Mumbai).\n\n" +
                        "•\tNearest Railway Station: Chinchpokli, Curry Road, and Lower Parel railway stations are close by.\n\n"
            }
            3 -> {
                imgMandal?.setImageResource(R.drawable.g10)
                supportActionBar?.title = resources.getString(R.string.chinchpoklicha_chintamani)
                tvDetail?.text = "Chinchpoklicha Chintamani, this is among the city’s oldest. \n\n" +
                        "\n" +
                        "•\tLocation: Near Chinchpokli Station\n\n" +
                        "Nearest Railway Station: Chinchpokli, Curry Road, and Lower Parel railway stations are close by\n\n"
            }
            4 -> {
                imgMandal?.setImageResource(R.drawable.g19)
                supportActionBar?.title = resources.getString(R.string.chembur_raja)
                tvDetail?.text = "Chembur ka Raja is famous idol in chembur camp. Idol is more than 16 feet tall and 14 feet wide of a traditional sitting Ganesha blessing his followers has been kept unchanged for long time.There are Ganesh idol in almost every lane in the area\n\n\n" +
                        "•\tLocation: Sindhi Camp Chembur\n\n" +
                        "•\tNearest Railway Station: Chembur Station \n"
            }
            5 -> {
                imgMandal?.setImageResource(R.drawable.g13)
                supportActionBar?.title = resources.getString(R.string.khetwadi_ganraj)
                tvDetail?.text = "The award-winning Khetwadi Ganraj is considered to be one of the most spectacular Ganesh idols in Mumbai. The idol is decked out in real gold jewelry. An added attraction when visiting the Khetwadi Ganraj is that there's a Ganesh idol in almost every lane in the area\n\n\n" +
                        "•\tLocation: 12th Lane Kehetwadi, Girgaum (south Mumbai).\n\n" +
                        "•\tNearest Railway Station: The nearest stations are Charni Road and Sandhurst Road.\n\n"
            }
            6 -> {
                imgMandal?.setImageResource(R.drawable.g16)
                supportActionBar?.title = resources.getString(R.string.andhericha_raja)
                tvDetail?.text = "The Andhericha Raja is to the Mumbai suburbs what the Lalbaugcha Raja is to south Mumbai. The idol isn't as towering or imposing. However, it has a reputation for fulfilling wishes. The mandal also usually has a novel theme and other attractions\n\n\n" +
                        "•\tLocation: Veera Desai Road, Azad Nagar, Andheri West (western Mumbai suburbs).\n\n" +
                        "•\tNearest Railway Station: Andheri.\n"
            }
        }
    }

    fun showPuneMandalDetails(position: Int){
        when(position){
            0 -> {
                imgMandal?.setImageResource(R.drawable.g23)
                supportActionBar?.title = resources.getString(R.string.shrimant_dagdusheth_halwai)
                tvDetail?.text = "Dagdusheth Halwai Ganesha has a history of more than 100 years and is participating in the Ganeshotsav from 1893"
            }
            1 -> {
                imgMandal?.setImageResource(R.drawable.g24)
                supportActionBar?.title = resources.getString(R.string.rajarshi_shahu_chowk)
                tvDetail?.text = "Ganesh mandal in Pune is famous of Hindu temples in India during Ganesh Chaturthi."
            }
            2 -> {
                imgMandal?.setImageResource(R.drawable.g25)
                supportActionBar?.title = resources.getString(R.string.tambadi_jogeshwari)
                tvDetail?.text = "This Ganesha Mandal has been participating in Ganeshotsav from 1893 and the Jogeshwari Ganpati is one of the most famous Sarvajanik Ganesha Festival."
            }
            3 -> {
                imgMandal?.setImageResource(R.drawable.g26)
                supportActionBar?.title = resources.getString(R.string.hutatma_babu_genu)
                tvDetail?.text = "This Sarvajanik Mandal is noted for its huge and beautiful Ganesh Pandals"
            }
            4 -> {
                imgMandal?.setImageResource(R.drawable.g27)
                supportActionBar?.title = resources.getString(R.string.manadai_ganeshotsav_mandal)
                tvDetail?.text = "This popular Sarvajanik Ganesh mandal is noted for its decorations and also for involvement in social causes. It is also runs a cooperative credit society."
            }
            5 -> {
                imgMandal?.setImageResource(R.drawable.g28)
                supportActionBar?.title = resources.getString(R.string.tulshibag_sarvajanik)
                tvDetail?.text = "This popular Ganesh Mandal at Budhwar Peth attracts thousands of devotees.  14-feet Ganesh idol of Tulshibag is popular among devotees."
            }
            6 -> {
                imgMandal?.setImageResource(R.drawable.g29)
                supportActionBar?.title = resources.getString(R.string.guruji_talim_ganapati)
                tvDetail?.text = "Established in 1887, Guruji Talim Ganapati is popular for its decorations and replicas of temples and popular monuments. One of the oldest mandals in Pune city."
            }
            7 -> {
                imgMandal?.setImageResource(R.drawable.g30)
                supportActionBar?.title = resources.getString(R.string.hatti_ganapati)
                tvDetail?.text = "This pandal is famous for its decorations. Pujas and rituals are attended by dedicated devotees every year."
            }
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
