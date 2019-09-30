package digispark.tech.ganapatiaarti.fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import digispark.tech.ganapatiaarti.MusicPojo
import digispark.tech.ganapatiaarti.MusicAdapter
import digispark.tech.ganapatiaarti.MusicPlayerActivity
import digispark.tech.ganapatiaarti.R
import digispark.tech.ganapatiaarti.constants.Constant
import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), MusicAdapter.ProgressBarInterface, View.OnClickListener {

    internal val TAG = HomeFragment::class.java.simpleName
    private var mAdView: AdView? = null
    lateinit internal var adapter: MusicAdapter
    internal var recyclerView: RecyclerView? = null
    lateinit internal var albumList: ArrayList<MusicPojo>
    private var progressBar: ProgressBar? = null
    private var ivPlayHome: ImageView? = null
    private var ivPauseHome: ImageView? = null
    private var viewRoot: View? = null
    private val mHandler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_home, container, false)
        initializer(viewRoot)
        return viewRoot
    }

    fun initializer(v: View?){
        mAdView = v?.findViewById(R.id.adView)
        ivPlayHome = v?.findViewById(R.id.ivPlayHome)
        ivPauseHome = v?.findViewById(R.id.ivPauseHome)
        progressBar = v?.findViewById(R.id.progressBar)

        albumList = ArrayList()
        prepareAlbums()

        recyclerView = v?.findViewById(R.id.recycler_view)
        adapter = MusicAdapter(activity, albumList, this)

        val mLayoutManager = GridLayoutManager(activity, 2)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
    }

    private val runAdAutomatic = object : Runnable{
        override fun run() {
            try {
                Log.d("test", "handler running")
                if (mAdView != null){
                    Log.d("test", "add shown in 20 secs")
                    UserInterfaceUtils.loadAd(mAdView)
                    mHandler.postDelayed(this, 20000)
                }
                else{
                    Log.d("test", "stop handler")
                    mHandler.removeCallbacksAndMessages(null)
                }
            }
            catch (e: Exception){
                e.printStackTrace()
                Log.d("test", "exception_runAdAutomatic " + e.message)
            }
        }
    }

    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album1, R.drawable.album2, R.drawable.album3, R.drawable.album4, R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.album1, R.drawable.img11, R.drawable.album7)

        var a = MusicPojo(activity!!.resources.getString(R.string.ekadantaya_vakratundaya), covers[0])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.sindoor_lal_chadayo), covers[1])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.sukhkarta_dukhharta), covers[2])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.lavthavti_vikrala), covers[3])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.durge_durgat_bhari), covers[4])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.yuge_atthavis), covers[5])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.yei_o_vitthale), covers[6])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.tu_sukhkarta), covers[7])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.jai_ganesh), covers[8])
        albumList.add(a)

        a = MusicPojo(activity!!.resources.getString(R.string.om_jai_jagadish),covers[9])
        albumList.add(a)
    }

    fun showPlayButton(show: Boolean) {
        if (show) {
            ivPlayHome?.visibility = View.VISIBLE
            ivPauseHome?.visibility = View.INVISIBLE
        } else {
            ivPlayHome?.visibility = View.INVISIBLE
            ivPauseHome?.visibility = View.VISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()

        runAdAutomatic.run()
        UserInterfaceUtils.loadAd(mAdView)

        if (!Constant.NOW_PLAYING_SONG_NAME.isEmpty() && Constant.NOW_PLAYING_SONG_NAME.length > 1) {
            val playingLayout = viewRoot?.findViewById<RelativeLayout>(R.id.playingLayout)
            playingLayout?.visibility = View.VISIBLE
            if (MusicPlayerActivity.mp != null){
                if (MusicPlayerActivity.mp?.isPlaying!!) {
                    showPlayButton(false)
                } else {
                    showPlayButton(true)
                }
                val playingSongName = viewRoot?.findViewById<TextView>(R.id.playingSongName)
                playingSongName?.setText(String.format(Locale.US, "%s: %s",
                        resources.getString(R.string.now_playing), Constant.NOW_PLAYING_SONG_NAME))
                playingSongName?.isSelected = true
            }
            playingLayout?.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.playingLayout -> {
                if (mHandler != null)
                    mHandler.removeCallbacksAndMessages(null)

                val intent = Intent(activity, MusicPlayerActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
            }
        }
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null)
    }

}
