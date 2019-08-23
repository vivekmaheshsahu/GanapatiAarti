package digispark.tech.ganapatiaarti

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

import com.google.android.gms.ads.AdView

import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils
import me.relex.circleindicator.CircleIndicator


class Mumbai_mandle : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: AlbumsAdapter_mandal? = null
    private var albumList: MutableList<Album>? = null
    private val XMENArray = ArrayList<Int>()
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mumbai_mandle)
        title = "Mumbai Mandals"
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        albumList = ArrayList()
        adapter = AlbumsAdapter_mandal(albumList!!)

        var mlayoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mlayoutmanager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = mlayoutmanager
        recyclerView!!.adapter = adapter

        prepareAlbums()
        init()

    }

    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album3, R.drawable.album2, R.drawable.album1, R.drawable.album4, R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.album1, R.drawable.img11, R.drawable.album7)

        var a = Album("GSB Seva Kings Circle", 13, covers[0])
        albumList!!.add(a)

        a = Album("Lalbaugcha Raja", 8, covers[1])
        albumList!!.add(a)

        a = Album("Ganesh Galli Mumbaicha Raja", 11, covers[2])
        albumList!!.add(a)

        a = Album("Chinchpoklicha Chintamani", 12, covers[3])
        albumList!!.add(a)

        a = Album("Chembur Raja", 14, covers[4])
        albumList!!.add(a)

        a = Album("Khetwadi Ganraj", 1, covers[5])
        albumList!!.add(a)

        a = Album("Andhericha Raja", 11, covers[8])
        albumList!!.add(a)

        adapter!!.notifyDataSetChanged()
    }

    private fun init() {
        for (i in XMEN.indices)
            XMENArray.add(XMEN[i])

        mPager = findViewById<View>(R.id.pager) as ViewPager
        mPager!!.adapter = MyAdapter(this@Mumbai_mandle, XMENArray)
        val indicator = findViewById<View>(R.id.indicator) as CircleIndicator
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private var mPager: ViewPager? = null
        private var currentPage = 0
        private val XMEN = arrayOf(R.drawable.g1, R.drawable.g5, R.drawable.g9, R.drawable.g11, R.drawable.g14, R.drawable.g19, R.drawable.g18)
        private val TAG = "MainActivity"
    }


}


