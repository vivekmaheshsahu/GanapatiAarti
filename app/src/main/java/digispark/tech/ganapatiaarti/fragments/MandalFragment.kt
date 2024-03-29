package digispark.tech.ganapatiaarti.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.ads.AdView
import digispark.tech.ganapatiaarti.MusicPojo
import digispark.tech.ganapatiaarti.MandalsAdapter
import digispark.tech.ganapatiaarti.MyPagerAdapter

import digispark.tech.ganapatiaarti.R
import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils
import me.relex.circleindicator.CircleIndicator
import java.util.*
import kotlin.collections.ArrayList

class MandalFragment : Fragment() {

    private var adView: AdView? = null
    private var rvMandal: RecyclerView? = null
    private var mandalAdapter: MandalsAdapter? = null
    private var albumList: ArrayList<MusicPojo>? = null
    private val viewPagerArray = ArrayList<Int>()
    lateinit internal var drawableArray: Array<Int>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_mandal, container, false)
        initializer(view)
        return view
    }

    companion object {
        private var mPager: ViewPager? = null
        private var currentPage = 0
        private val XMEN = arrayOf(R.drawable.g1, R.drawable.g5, R.drawable.g9, R.drawable.g11,
                R.drawable.g14, R.drawable.g19, R.drawable.g18)
    }

    fun initializer(v: View){
        adView = v.findViewById(R.id.adView)
        rvMandal = v.findViewById(R.id.rvMandal)
        albumList = java.util.ArrayList()

        if (Constant.LANGUAGE.equals("mumbai")){
            drawableArray = arrayOf(R.drawable.g1, R.drawable.g5, R.drawable.g9, R.drawable.g11,
                    R.drawable.g14, R.drawable.g19, R.drawable.g18)
        }
        else if (Constant.LANGUAGE.equals("pune")){
            drawableArray = arrayOf(R.drawable.g23, R.drawable.g27, R.drawable.g25, R.drawable.g26,
                    R.drawable.g24, R.drawable.g28, R.drawable.g29, R.drawable.g30)
        }

        mandalAdapter = MandalsAdapter(albumList!!)

        rvMandal!!.adapter = mandalAdapter

        prepareAlbums()
        init(v)
    }

    override fun onResume() {
        super.onResume()
        UserInterfaceUtils.loadAd(adView)
    }

    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album3, R.drawable.album2, R.drawable.album1, R.drawable.album4,
                R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.album1, R.drawable.img11, R.drawable.album7)

        if (Constant.LANGUAGE.equals("mumbai")){
            var a = MusicPojo(activity?.resources?.getString(R.string.gsb_seva_kings_circle)!!,covers[0])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.lalbaugcha_raja)!!,covers[1])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.ganesh_galli)!!,covers[2])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.chinchpoklicha_chintamani)!!, covers[3])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.chembur_raja)!!, covers[4])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.khetwadi_ganraj)!!, covers[5])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.andhericha_raja)!!, covers[8])
            albumList!!.add(a)
        }
        else if (Constant.LANGUAGE.equals("pune")){
            var a = MusicPojo(activity?.resources?.getString(R.string.shrimant_dagdusheth_halwai)!!, covers[0])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.rajarshi_shahu_chowk)!!,covers[1])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.tambadi_jogeshwari)!!, covers[2])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.hutatma_babu_genu)!!, covers[3])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.manadai_ganeshotsav_mandal)!!, covers[4])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.tulshibag_sarvajanik)!!,covers[5])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.guruji_talim_ganapati)!!, covers[8])
            albumList!!.add(a)

            a = MusicPojo(activity?.resources?.getString(R.string.hatti_ganapati)!!, covers[8])
            albumList!!.add(a)
        }

        mandalAdapter?.notifyDataSetChanged()
    }


    private fun init(v: View) {
        for (i in XMEN.indices)
            viewPagerArray.add(XMEN[i])

        mPager = v.findViewById<View>(R.id.pager) as ViewPager
        mPager!!.adapter = MyPagerAdapter(activity!!, viewPagerArray)
        val indicator = v.findViewById<View>(R.id.indicator) as CircleIndicator
        indicator.setViewPager(mPager)

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == XMEN.size) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2500, 2500)
    }

}
