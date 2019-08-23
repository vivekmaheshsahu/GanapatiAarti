package digispark.tech.ganapatiaarti.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView

import digispark.tech.ganapatiaarti.R
import digispark.tech.ganapatiaarti.adapters.AartiAdapter
import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.pojo.AlbumNew
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils
import java.util.ArrayList

class AartiFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: AartiAdapter? = null
    private var albumList: MutableList<AlbumNew>? = null
    private var mAdView: AdView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_aarti, container, false)
        initializer(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        UserInterfaceUtils.loadAd(mAdView)
    }

    fun initializer(v: View){
        mAdView = v.findViewById(R.id.adView)
        recyclerView = v.findViewById(R.id.recycler_view) as RecyclerView
        albumList = ArrayList()
        adapter = AartiAdapter(albumList as ArrayList<AlbumNew>)
        recyclerView?.setAdapter(adapter)
        prepareAartis()
    }

    private fun prepareAartis(){
        val rawArrayHindi = intArrayOf(R.raw.shendur_lal_chhadayo_hin,
                R.raw.jai_ganesh_deva_hin, R.raw.jai_ambe_gauri_hin,
                R.raw.shiv_ji_aarti_hin, R.raw.om_jai_jagadish_hin, R.raw.krishna_aarti_hin,
                R.raw.shani_aarti_hin, R.raw.lakshmi_aarti_hin, R.raw.santoshi_mata_aarti_hin,
                R.raw.kali_mata_aarti_hin, R.raw.brihaspati_aarti_hin, R.raw.ghalin_lotangan_mar,
                R.raw.mantrapushpanjali_mar)

        val rawArrayMarathi = intArrayOf(R.raw.sukhkarta_mar, R.raw.durge_durgat_bhari_mar,
                R.raw.lavlavthi_vikrala_mar, R.raw.datta_aarti_mar, R.raw.yuge_atthavis_mar,
                R.raw.yei_o_vithhale_maze_mar, R.raw.aarti_dnyanraja_mar, R.raw.ghalin_lotangan_mar,
                R.raw.sada_sarvda_yog_mar, R.raw.mantrapushpanjali_mar)

        val rawArrayHinEnglish = intArrayOf(R.raw.shendur_lal_chhadayo_eng,
                R.raw.jai_ganesh_deva_eng, R.raw.jai_ambe_gauri_eng,
                R.raw.shiv_ji_aarti_eng, R.raw.om_jai_jagadish_eng, R.raw.krishna_aarti_eng,
                R.raw.shani_aarti_eng, R.raw.lakshmi_aarti_eng, R.raw.santoshi_mata_aarti_eng,
                R.raw.kali_mata_aarti_eng, R.raw.brihaspati_aarti_eng, R.raw.ghalin_lotangan_eng,
                R.raw.mantrapushpanjali_eng)

        val rawArrayMarEnglish = intArrayOf(R.raw.sukhkarta_eng, R.raw.durge_durgat_bhari_eng,
                R.raw.lavlavthi_vikrala_eng, R.raw.datta_aarti_eng, R.raw.yuge_atthavis_eng,
                R.raw.yei_o_vithhale_maze_eng, R.raw.aarti_dnyanraja_eng, R.raw.ghalin_lotangan_eng,
                R.raw.sada_sarvda_yog_eng, R.raw.mantrapushpanjali_eng)

        val coversHindi = intArrayOf(R.drawable.ganesh2,R.drawable.ganesh3, R.drawable.durga_devi,
                R.drawable.shankar1, R.drawable.vishnu_dev, R.drawable.krishna,
                R.drawable.shani_dev, R.drawable.lakshmi, R.drawable.santoshi_mata,
                R.drawable.kalimata, R.drawable.brihaspati_dev, R.drawable.pushpanjali)

        val coversMarathi = intArrayOf(R.drawable.ganesh1, R.drawable.durga_devi, R.drawable.shankar1,
                R.drawable.datta, R.drawable.vitthal2, R.drawable.vitthal1,
                R.drawable.dnyan_raja, R.drawable.ganesh3, R.drawable.pushpanjali)

        albumList?.clear()
        adapter?.notifyDataSetChanged()

        if (Constant.LANGUAGE == "हिंदी"){
            var a = AlbumNew("शेंदूर लाल चढायो",rawArrayHindi[0],  coversHindi[0])
            albumList?.add(a)
            a = AlbumNew("जय गणेश जय गणेश देवा",rawArrayHindi[1], coversHindi[1])
            albumList?.add(a)
            a = AlbumNew("जय अम्बे गौरी मैया", rawArrayHindi[2],coversHindi[2])
            albumList?.add(a)
            a = AlbumNew("शिव आरती",rawArrayHindi[3], coversHindi[3])
            albumList?.add(a)
            a = AlbumNew("ओम जय जगदीश हरे", rawArrayHindi[4],coversHindi[4])
            albumList?.add(a)
            a = AlbumNew("कृष्ण आरती",rawArrayHindi[5], coversHindi[5])
            albumList?.add(a)
            a = AlbumNew("शनि देवजी की आरती",rawArrayHindi[6], coversHindi[6])
            albumList?.add(a)
            a = AlbumNew("लक्ष्मीजी की आरती",rawArrayHindi[7], coversHindi[7])
            albumList?.add(a)
            a = AlbumNew("संतोषी माता की आरती",rawArrayHindi[8], coversHindi[8])
            albumList?.add(a)
            a = AlbumNew("कालीमाता आरती",rawArrayHindi[9], coversHindi[9])
            albumList?.add(a)
            a = AlbumNew("बृहस्पति देवता की आरती",rawArrayHindi[10], coversHindi[10])
            albumList?.add(a)
            a = AlbumNew("घालीन लोटांगण", rawArrayHindi[11], coversHindi[1])
            albumList?.add(a)
            a = AlbumNew("मंत्रपुष्पांजली", rawArrayHindi[12], coversHindi[11])
            albumList?.add(a)
        }
        else if (Constant.LANGUAGE == "मराठी"){
            var a = AlbumNew("सुखकर्ता दुखहर्ता", rawArrayMarathi[0],coversMarathi[0])
            albumList?.add(a)
            a = AlbumNew("दुर्गे दुर्घट भारी", rawArrayMarathi[1], coversMarathi[1])
            albumList?.add(a)
            a = AlbumNew("लवथवती विक्राळा", rawArrayMarathi[2], coversMarathi[2])
            albumList?.add(a)
            a = AlbumNew("त्रिगुणात्मक त्रैमूर्ती दत्त", rawArrayMarathi[3],coversMarathi[3])
            albumList?.add(a)
            a = AlbumNew("युगें अठ्ठावीस", rawArrayMarathi[4],coversMarathi[4])
            albumList?.add(a)
            a = AlbumNew("येई हो विठ्ठले माझे माऊली ये", rawArrayMarathi[5], coversMarathi[5])
            albumList?.add(a)
            a = AlbumNew("आरती ज्ञानराजा", rawArrayMarathi[6], coversMarathi[6])
            albumList?.add(a)
            a = AlbumNew("घालीन लोटांगण", rawArrayMarathi[7], coversMarathi[7])
            albumList?.add(a)
            a = AlbumNew("सदा सर्वदा योग", rawArrayMarathi[8], coversMarathi[7])
            albumList?.add(a)
            a = AlbumNew("मंत्रपुष्पांजली", rawArrayMarathi[9], coversMarathi[8])
            albumList?.add(a)
        }
        else if (Constant.LANGUAGE == "hindi"){
            var a = AlbumNew("Shendur Lal Chadayo",rawArrayHinEnglish[0],  coversHindi[0])
            albumList?.add(a)
            a = AlbumNew("Jai Ganesh Jai Ganesh Deva",rawArrayHinEnglish[1], coversHindi[1])
            albumList?.add(a)
            a = AlbumNew("Jai Ambe Gauri Maiya", rawArrayHinEnglish[2],coversHindi[2])
            albumList?.add(a)
            a = AlbumNew("Shiv Ji Ki Aarti",rawArrayHinEnglish[3], coversHindi[3])
            albumList?.add(a)
            a = AlbumNew("Om Jai Jagadish Hare", rawArrayHinEnglish[4],coversHindi[4])
            albumList?.add(a)
            a = AlbumNew("Shri Krishna Aarti",rawArrayHinEnglish[5], coversHindi[5])
            albumList?.add(a)
            a = AlbumNew("Shani Dev Ji Ki Aarti",rawArrayHinEnglish[6], coversHindi[6])
            albumList?.add(a)
            a = AlbumNew("Lakshmi Ji Ki Aarti",rawArrayHinEnglish[7], coversHindi[7])
            albumList?.add(a)
            a = AlbumNew("Santoshi Mata Ki Aarti",rawArrayHinEnglish[8], coversHindi[8])
            albumList?.add(a)
            a = AlbumNew("Kalimata Aarti",rawArrayHinEnglish[9], coversHindi[9])
            albumList?.add(a)
            a = AlbumNew("Brihaspati devata ki aarti",rawArrayHinEnglish[10], coversHindi[10])
            albumList?.add(a)
            a = AlbumNew("Ghalin Lotangan", rawArrayHinEnglish[11], coversHindi[1])
            albumList?.add(a)
            a = AlbumNew("Mantra Pushpanjali", rawArrayHinEnglish[12], coversHindi[11])
            albumList?.add(a)
        }
        else if (Constant.LANGUAGE == "marathi"){
            var a = AlbumNew("Ganapati Aarti", rawArrayMarEnglish[0],coversMarathi[0])
            albumList?.add(a)
            a = AlbumNew("Durga Mata Aarti", rawArrayMarEnglish[1], coversMarathi[1])
            albumList?.add(a)
            a = AlbumNew("Shankar Aarti", rawArrayMarEnglish[2], coversMarathi[2])
            albumList?.add(a)
            a = AlbumNew("Datta Aarti", rawArrayMarEnglish[3],coversMarathi[3])
            albumList?.add(a)
            a = AlbumNew("Vithhal Aarti", rawArrayMarEnglish[4],coversMarathi[4])
            albumList?.add(a)
            a = AlbumNew("Yei ho vithhale maaze", rawArrayMarEnglish[5], coversMarathi[5])
            albumList?.add(a)
            a = AlbumNew("Dnyandev Aarti", rawArrayMarEnglish[6], coversMarathi[6])
            albumList?.add(a)
            a = AlbumNew("Ghalin Lotangan", rawArrayMarEnglish[7], coversMarathi[7])
            albumList?.add(a)
            a = AlbumNew("Sada Sarvada Yog", rawArrayMarEnglish[8], coversMarathi[7])
            albumList?.add(a)
            a = AlbumNew("Mantra Pushpanjali", rawArrayMarEnglish[9], coversMarathi[8])
            albumList?.add(a)
        }
        adapter?.notifyDataSetChanged()
    }
}
