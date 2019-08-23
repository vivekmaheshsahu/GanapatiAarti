package digispark.tech.ganapatiaarti

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.ads.AdView

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class written_eng_detail_aarti : AppCompatActivity() {
    internal var intValue: Int = 0
    internal var a: Int = 0
    private var adView: AdView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_written_eng_detail_aarti)
        val intent = intent
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        val height = metrics.heightPixels
        val width = metrics.widthPixels
        val b1: Button
        val b2: Button
        b1 = findViewById<View>(R.id.b1) as Button
        b2 = findViewById<View>(R.id.b2) as Button
        b1.width = width / 2 - 20
        b2.width = width / 2 - 20

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.loadAd(adView)

        intValue = intent.getIntExtra("songindex", 0)
        val t1: TextView
        val t2: TextView
        t1 = findViewById<View>(R.id.txt) as TextView
        t2 = findViewById<View>(R.id.t1) as TextView
        if (intValue == 1) {
            t2.text = "SHENDUR LAL CHADHAYO"
            t1.text = "Shendur lal chadhayo" +
                    " Achchha gajmukhko" +
                    " Dondil lal biraje" +
                    " Sut gauriharko" +
                    " Hath liye gudladdu" +
                    " Sai survarko" +
                    " Mahima kahe na jay lagat hoo padko\n" +
                    "\n" +
                    "Jai dev jai dev" +
                    " Shri ganaraj vidhyasukhdata" +
                    " Dhanya tumara darshan" +
                    " mera man ramata " +
                    " Jai dev jai dev\n" +
                    "\n" +
                    "Bhavbhagati se" +
                    " koi sharanagat ave" +
                    " Santat samat sabahi bharpur pave" +
                    "\n" +
                    "Aise tum maharaj moko ati bhave" +
                    " Gosavinandan nishidin gun gave,\n" +
                    "\n" +
                    "Jai dev jai dev\n" +
                    " Shri ganaraj vidhyasukhdata" +
                    " Dhanya tumara darshan" +
                    " Mera man ramata"
        }
        if (intValue == 2) {
            t2.text = "LAVATHAVATI VIKRALA"
            t1.text = "Lavathavati Vikrala Brahmandi Mala\n" +
                    "Vishe Kantha Kala Trinetri Jwala।\n" +
                    "Lavanya Sundara Mastaki Bala।\n" +
                    "Tethuniya Jala Nirmala Vahe Jhulajhula॥\n" +
                    "Jai Deva Jai Deva Jai Shri Shankara।\n" +
                    "Aarti Ovalu Tuja Karpuragaura॥\n" +
                    "\n" +
                    "Karpuragaura Bhola Nayani Vishala।\n" +
                    "Ardhangi Parvati Sumananchya Mala।\n" +
                    "Vibhutiche Udhalana Shitikantha Nila।\n" +
                    "Aisa Shankar Shobhe Umavelhala॥\n" +
                    "Jai Deva Jai Deva Jai Shri Shankara।\n" +
                    "Aarti Ovalu Tuja Karpuragaura॥\n" +
                    "\n" +
                    "Devi Daityi Sagaramanthana Pai Kele।\n" +
                    "Tyamaji Avachita Halahala Je Uthile।\n" +
                    "Te Tva Asurapane Prashana Kele।\n" +
                    "Nilakantha Nama Prasiddha Jhale॥\n" +
                    "Jai Deva Jai Deva Jai Shri Shankara।\n" +
                    "Aarti Ovalu Tuja Karpuragaura॥\n" +
                    "\n" +
                    "Vyaghrambara Phanivardhara Sundara Madanari।\n" +
                    "Panchanana Manamohana Munijana Sukhakari।\n" +
                    "Shatakotiche Beej Vache Uchchari।\n" +
                    "Raghukulatilaka Ramadasa Antari॥\n" +
                    "Jai Deva Jai Deva Jai Shri Shankara।\n" +
                    "Aarti Ovalu Tuja Karpuragaura॥\n"
        }
        if (intValue == 0) {
            t2.text = "Sukhkarta Dukhharta"
            t1.text = "Sukhkarta Dukhharta Varta Vighnachi \n" +
                    "Nurvi Purvi Prem Krupa Jayachi \n" +
                    "Sarvangi Sundar Uti Shendurachi \n" +
                    "Kanti Jhalke Mal Mukataphalaanchi..\n\n" +
                    "Jaidev Jaidev Jai Mangal Murti \n" +
                    "Darshan Maatre Man: Kaamna Phurti \n" +
                    "Ratnakhachit Phara Tujh Gaurikumra \n" +
                    "Chandanaachi Uti Kumkumkeshara \n" +
                    "Hirejadit Mukut Shobhato Bara \n" +
                    "Runjhunati Nupure Charani Ghagriya \n" +
                    "Jaidev Jaidev Jai Mangal Murti \n\n" +
                    "Lambodar Pitaambar Phanivarvandana \n" +
                    "Saral Sond Vakratunda Trinayana \n" +
                    "Das Ramacha Vat Pahe Sadana \n" +
                    "Sankati Pavave Nirvani Rakshave Survarvandana \n" +
                    "Jaidev Jaidev Jai Mangal Murti "
        }
        if (intValue == 5) {
            t2.text = "Yei oh Vitthale Majhe"
            t1.text = "Yei ho vitthale maze maooliye I\n" +
                    "Niddhalavari kar theooni vat mi pahi II \n\n" +
                    "Aliya geliya hati dadhi nirop II\n" +
                    "Pandharpuri ahe maza maybap II Yei II\n\n" +
                    "Pivala pitambar kaisa gagani jhadakala II\n" +
                    "Garudavar baisun maza kaivari ala II Yei II\n\n" +
                    "Vithibache rajya amha nitya deepwali II\n" +
                    "Vishnudas nama jive bhave ovalee II Yei II\n\n" +
                    "Aso naso bhav amha tujhiya thaya I\n" +
                    "Krupadrashti pahe maza pandhariraya\n" +
                    "II Yei II"
        }
        if (intValue == 3) {
            t2.text = "Durge durghat bhari"
            t1.text = "Durge durghat bhari tujvin sansari " +
                    "Anathnathe ambe karuna vistari " +
                    "Vari vari janam marante vari " +
                    "Hari padalo ata sankat nivari II 1 II\n" +
                    "\n" +
                    "\n" +
                    "Jaya devi jaya devi mahisha surmathini \n" +
                    "Survar ishwar varde tarak sanjivani II \n" +
                    "\n" +
                    "\n" +
                    "Tujaveen bhuvani pahata tuj aise nahi \n" +
                    "Chari shramale parantu n bolve kahi \n" +
                    "Sahi vivad karita padile pravahi \n" +
                    "Te tu bhaktalagi pavasi lavlahi II 2 II\n" +
                    "\n" +
                    "\n" +
                    "Prasanna vadane prasanna hosi nijdasa \n" +
                    "Kleshapasuni sodivi todi bhavpasha \n" +
                    "Ambe tujvachun kon purvil asha \n" +
                    "Narhari tallin jhala padpankajlesha II 3 II" +
                    "\n\n Jai Devi Jai Devi Mahishasuramathini " +
                    "Suravara Ishwara Varade Taraka Sanjivani II"
        }
        if (intValue == 7) {
            t2.text = "Om Jai Jagadish Hare"
            t1.text = "Om Jai Jagadish Hare " +
                    "Swami Jaya Jagadish Hare " +
                    "Bhakta janon ke sankat " +
                    "" +
                    "Bhakta janon ke sankat Kshan me door kar " +
                    "Om Jai Jagadish Hare " +
                    "\n\n" +
                    "Jo dhyave phal paave " +
                    "Dhukh vinashe man ka " +
                    "Swami dhukh vinashe man ka " +
                    "Sukha sampati Ghar aave " +
                    "Sukha sampati Ghar aave " +
                    "Kashht mite tan ka " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Mata pita tum mere " +
                    "Sharan padun mai kis ki " +
                    "Swami sharan padum mai kis ki " +
                    "Tum bina aur na doojaa " +
                    "Tum bina aur na doojaa " +
                    "Asha karun mai kis ki " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Tum pooran Paramatma " +
                    "Tum Antaryaami " +
                    "Swami Tum Antaryaami " +
                    "Para brahma Parameshwara " +
                    "Para brahma Parameshwara " +
                    "Tum sab ke Swami " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Tum karuna ke saagar " +
                    "Tum palan karta " +
                    "Swami Tum palan karta " +
                    "Mai sevak tum swaami " +
                    "Mai sevak tum swaami " +
                    "Kripa karo bhartaa " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Tum ho ek agochar " +
                    "Sab ke prana pati " +
                    "Swami sab ke prana pati " +
                    "Kis vidhi miloon dayamaya " +
                    "Kisi vidhi miloon dayamaya " +
                    "Tum ko mai kumati " +
                    "Om Jai Jagadish Hare\n" +
                    "\n" +
                    "Deena bandhu dukh hartaa " +
                    "Tum rakshak mere " +
                    "Swami tum rakshak mere " +
                    "Apane hast uthao " +
                    "Apane hast uthao " +
                    "Dwar khada mai tere " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Vishaya vikar mithao " +
                    "Paap haro deva " +
                    "Swami paap haro deva " +
                    "Shraddha bhakti badhao " +
                    "Shraddha bhakti badhao " +
                    "Santan ki seva " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Tan man dhan sab kuch hai tera " +
                    "Swami sab kuch hai tera " +
                    "Tera tujh ko arpan " +
                    "Tera tujh ko arpan " +
                    "Kya laage mera " +
                    "Om Jai Jagadish Hare \n" +
                    "\n" +
                    "Om Jai Jagadish Hare " +
                    "Swami Jai Jagadish Hare " +
                    "Bhakta janon ke sankat " +
                    "Bhakta janon ke sankat " +
                    "Kshan me door kare " +
                    "Om Jai Jagadish Hare"
        }
        if (intValue == 6) {
            t2.text = "Jai Ganesh, Jai Ganesh Deva"
            t1.text = "Jai Ganesh, Jai Ganesh, Jai Ganesh Deva Mata Jaaki Parvati Pita Mahadeva\n" +
                    "\n" +
                    "Ek Dant Dayavant, Chaar Bhuja Dhaari " +
                    "Maathe Pe Sindhoor Sohe, Muse Ki Savari " +
                    "\n" +
                    "\n" +
                    "Paan Chadhe, Phul Chadhe, Aur Chadhe Meva " +
                    "Ladduan Ka Bhog Lage, Sant Kare Seva\n" +
                    "\n" +
                    "Jai Ganesh, Jai Ganesh, Jai Ganesh Deva " +
                    "Mata Jaaki Parvati Pita Mahadeva\n" +
                    "\n" +
                    "Andhan Ko Aankh Det, Kodhin Ko Kaaya " +
                    "Baanjhan Ko Putra Det, Nirdhan Ko Maaya " +
                    "Surya Shaam Sharan Aye, Safalki Je Seva " +
                    "Mata Jaaki Parvati Pita Mahadeva\n" +
                    "\n" +
                    "Jai Ganesh, Jai Ganesh, Jai Ganesh Deva " +
                    "Mata Jaaki Parvati, Pita Mahadeva"

        }
        if (intValue == 10) {
            t2.text = "Jai ambe gauri maiya jai shyama gauri"
            t1.text = "Jai ambe gauri maiya jai shyama gauri\n" +
                    "Tumako nishadin dhyawat hari bramha shivaji || Maiya Jai..||\n" +
                    "\n" +
                    "Mang sindur virajat tiko mrigamad ko\n" +
                    "Ujjwal se dou naina chandrawadan niko || Maiya..\n" +
                    "\n" +
                    "Kanak saman kalevar raktambar raje\n" +
                    "Rakt pushp gal mala kanthan par saje || Maiya..\n" +
                    "\n" +
                    "Kehari wahan rajat khadag khappar dhari\n" +
                    "Sur-nar-munijan sewat tinake dukhahari || Maiya..\n" +
                    "\n" +
                    "Kaanan kundal shobhit nasagre moti\n" +
                    "Kotik chandr diwakar rajat sam jyoti || Maiya..\n" +
                    "\n" +
                    "Shumbh-nishumbh bidare mahishasur ghati\n" +
                    "Dhumr vilochan naina nishadin madamati || Maiya..\n" +
                    "\n" +
                    "Chand-mund sanhare shonit bij hare\n" +
                    "Madhu-kaitabh dou mare sur bhayahin kare || Maiya..\n" +
                    "\n" +
                    "Bramhani rudranitum kamala rani\n" +
                    "Agam nigam bakhanitum shiv patarani || Maiya..\n" +
                    "\n" +
                    "Chausath yogini mangal gawatnritya karat bhairu\n" +
                    "Bajat tal mridangaaru baajat damaru || Maiya..\n" +
                    "\n" +
                    "Tum hi jag ki mata tum hi ho bharata\n" +
                    "Bhaktan ki dukh harta sukh sampati karta || Maiya..\n" +
                    "\n" +
                    "Bhuja char ati shobhivaramudra dhari\n" +
                    "Manwanchhit fal pawat sewat nar nari || Maiya..\n" +
                    "\n" +
                    "Kanchan thal virajat agar kapur bati\n" +
                    "Shrimalaketu mein rajat koti ratan jyoti || Maiya..\n" +
                    "\n" +
                    "Shri ambeji ki arati jo koi nar gave\n" +
                    "Kahat shivanand swami sukh-sampatti pave || Maiya\n"
        }
        if (intValue == 4) {
            t2.text = "Yuge atthavis"
            t1.text = "Yuge atthavis vithevari Ubha\n" +
                    "vaamaangi rakhumai rise rivya shobha\n" +
                    "punralika bheti parbrahma aalegaa\n" +
                    "Charni vaahe bheema Urrhari jaga\n" +
                    "jaya rev jaya rev jaya panruranga\n" +
                    "rakhumaai vallabha raaichya vallabha paave jivlaga\n" +
                    "\n" +
                    "tulasimala gala kar thevuni kati\n" +
                    "kaanse pitambar kasturi Lallati\n" +
                    "rev survar nitya Yeti bheti\n" +
                    "garur hanumant purhe Ubhe raahati\n" +
                    "jaya rev jaya rev jaya panruranga\n" +
                    "rakhumaai vallabha raaichya vallabha paave jivlaga\n" +
                    "\n" +
                    "rhannya venunaar anukshetrapala\n" +
                    "suvarnaachi kamale vanmala gala\n" +
                    "raai rakhumabai raaniyaa sakala\n" +
                    "Ovaliti raja vithoba saanvala\n" +
                    "jaya rev jaya rev jaya panruranga\n" +
                    "rakhumaai vallabha raaichya vallabha paave jivlaga\n" +
                    "\n" +
                    "Ovalu aaratya kurvanryaa Yeti\n" +
                    "Chanrrabhagemaji soruniyaa reti rinryaa pataka vaishnav naachti\n" +
                    "panrharicha mahima varnava kiti\n" +
                    "jaya rev jaya rev jaya panruranga\n" +
                    "rakhumaai vallabha raaichya vallabha paave jivlaga\n" +
                    "\n" +
                    "\n" +
                    "aasharhi kartiki bhaktajan Yeti\n" +
                    "Oh sarhujan Yeti\n" +
                    "Chanrrabhagemaaji snaane je kariti\n" +
                    "rarshan helamaatre tayaan hoya mukti\n" +
                    "keshavasi namrev marhavasi namrev bhaaven Ovaaliti\n" +
                    "jaya rev jaya rev jaya panruranga\n" +
                    "rakhumaai vallabha raaichya vallabha paave jivlaga\n"
        }
        if (intValue == 8) {
            t2.text = "Shri Dynarajachi Aarti"
            t1.text = "Arti dyanraja || mahakaivalyateja ||" +
                    "Sevati sadhusant || Manu vedhala maza || 1 ||" +
                    "Arti dyanraja ||\n\n" +
                    "Lopale gyan jagi || Hit nenati koni ||" +
                    "Avatar panduranga || Nam thevile gyani || 2 ||" +
                    "Arti dyanraja ||\n\n" +
                    "Kanakache Tatt kari  ||  Ubhya gopika nari || " +
                    "narad tumbaraho || Sam gayan kari ||  3 || " +
                    "Arti dyanraja ||\n\n" +
                    "Prakat gyuha bole || Vishva brahmachi kele || " +
                    "Ram janadarni || Paai mastak thele || 4 ||" +
                    "Arti dyanraja ||"
        }
        if (intValue == 9) {
            t2.text = "Ghalin lotangan vandin charan"
            t1.text = "Ghalin lotangan vandin charan ||" +
                    "Dolyani pahin rup tujhe || " +
                    "Preme alingan anande pujin || " +
                    "Bhave ovaleen mhane nama || 1 || \n" +
                    "\n" +
                    "Twamev mata cha Pita twamev || " +
                    "Twamev bandhusch sakha twamev || " +
                    "Twamev Vidhya dravinam twamev || " +
                    "Twamev sarwam mam dev dev || 2 || \n" +
                    "\n" +
                    "Kayena vacha manasendriyenva || " +
                    "Buddhayatmna va prakrutiswabhavat || " +
                    "Karomi yadhyat sakalam parasmai || " +
                    "Narayanayeti samarpayami || 3 || \n" +
                    "\n" +
                    "Achyutam keshavam ramnarayanam || " +
                    "krushanadamodaram vasudevam bhaje || " +
                    "Shridharam Madhavam gopikavallabham || " +
                    "Janaki nayakam ramchandra bhaje || 4 || \n" +
                    "\n" +
                    "Hare ram hare ram ram ram hare hare || " +
                    "Hare krishna hare krishna Krishna Krishna hare" +
                    "hare || " +
                    "Hare ram hare ram ram ram hare hare || " +
                    "Hare krishna hare krishna Krishna Krishna hare" +
                    "hare || " +
                    "Hare ram hare ram ram ram hare hare || " +
                    "Hare krishna hare krishna Krishna Krishna hare" +
                    "hare ||"
        }
        if (intValue == 11) {
            t2.text = "Shri Dattachi Aarti"
            t1.text = "Trigunatmaka Traimurti Datta Ha Jana।\n" +
                    "Triguni Avatara Trailokyarana।\n" +
                    "Neti Neti Shabda Na Ye Anumana।\n" +
                    "Suravara Munijana Yogi Samadhi Na Ye Dhyana॥\n" +
                    "\n" +
                    "Jai Deva Jai Deva Jai Shri Gurudatta।\n" +
                    "Aarti Ovalita Harali Bhavachinta॥\n" +
                    "\n" +
                    "Sabahya Abhyantari Tu Ek Datta।\n" +
                    "Abhagyasi Kaisi Na Kale Hi Maata।\n" +
                    "Parahi Paratali Tethe Kaicha Heta।\n" +
                    "Janmamaranacha Puralase Anta॥\n" +
                    "\n" +
                    "Jai Deva Jai Deva Jai Shri Gurudatta।\n" +
                    "Aarti Ovalita Harali Bhavachinta॥\n" +
                    "\n" +
                    "Datta Yeuniya Ubha Thakala।\n" +
                    "Sadbhave Sashtange Pranipata Kela।\n" +
                    "Prasanna Houni Ashirwada Didhala।\n" +
                    "Janmamaranacha Phera Chukavila॥\n" +
                    "\n" +
                    "Jai Deva Jai Deva Jai Shri Gurudatta।\n" +
                    "Aarti Ovalita Harali Bhavachinta॥\n" +
                    "\n" +
                    "Datta Datta Aise Lagale Dhyana।\n" +
                    "Harapale Mana Jhale Unmana।\n" +
                    "Mi Tu Panachi Jhali Bolavana।\n" +
                    "Eka Janardani Shri Datta Dhyana॥\n" +
                    "\n" +
                    "Jai Deva Jai Deva Jai Shri Gurudatta।\n" +
                    "Aarti Ovalita Harali Bhavachinta॥"
        }
        if (intValue == 12) {
            t2.text = "Mantra pushpanjali"
            t1.text = "Om yadnyen yadnyamayajant devastaani dharmani pradhamanyasan |\n" +
                    "Te ha nakam mahimanaha sachant yatra purve sadhyaha santi devaha ||\n\n" +
                    "Om rajadhirajaya prashyasahine namo vayam vaishranaaya kurmehe |\n" +
                    "Sa me kamanam kamkamaya mahyam kamemavaro vaishrano dadaatu |\n" +
                    "Kuberaaya vaishrano maharajaya namaha |\n\n" +
                    "Om swasti samrajyam bhoujyam swarajyam vairajyam parameshthyam\n" +
                    "Rajyam maharajyamaadhiptyamayam samantparyaayee syaatsarvabhoumaha |\n\n" +
                    "Sarvayush aantadaparardhatpruthivyai samudraparyantaya ekraliti\n" +
                    "Aavikshitsya kamprervimve devaha sabhasad itee |"
        } else {

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@written_eng_detail_aarti, Written_aarti_english::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this@written_eng_detail_aarti, Written_aarti_english::class.java)
        startActivity(intent)
    }

    fun back(view: View) {
        if (intValue == 0) {
            a = 12
            val intent = Intent(this@written_eng_detail_aarti, written_eng_detail_aarti::class.java)
            intent.putExtra("songindex", a)
            startActivity(intent)
        } else {
            val value = intValue
            val second = value - 1
            val intent = Intent(this@written_eng_detail_aarti, written_eng_detail_aarti::class.java)
            intent.putExtra("songindex", second)
            startActivity(intent)
        }

    }

    fun next(view: View) {

        if (intValue == 12) {
            a = 0
            val intent = Intent(this@written_eng_detail_aarti, written_eng_detail_aarti::class.java)
            intent.putExtra("songindex", a)
            startActivity(intent)
        } else {
            val value = intValue
            val second = value + 1
            val intent = Intent(this@written_eng_detail_aarti, written_eng_detail_aarti::class.java)
            intent.putExtra("songindex", second)
            startActivity(intent)
        }


    }
}
