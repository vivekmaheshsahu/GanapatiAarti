package digispark.tech.ganapatiaarti

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.ads.AdView

import java.util.ArrayList

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class written_aarti_hindi : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: AlbumsAdapter_written_hindi? = null
    private var albumList: MutableList<Album>? = null
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_written_aarti_english)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.loadAd(adView)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        albumList = ArrayList()
        adapter = AlbumsAdapter_written_hindi(albumList!!)

        var mlayoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mlayoutmanager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = mlayoutmanager
        recyclerView!!.adapter = adapter

        prepareAlbums()


    }

    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album3, R.drawable.album2, R.drawable.album4, R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.img11, R.drawable.album7, R.drawable.album1, R.drawable.album3, R.drawable.datta, R.drawable.album_2, R.drawable.img11, R.drawable.album11)

        var a = Album("सुखकर्ता दुखहर्ता", 13, covers[0])
        albumList!!.add(a)

        a = Album("शेंदूर लाल चढायो", 8, covers[1])
        albumList!!.add(a)

        a = Album("लवथवती विक्राळा", 12, covers[2])
        albumList!!.add(a)

        a = Album("दुर्गे दुर्घट भारी", 14, covers[3])
        albumList!!.add(a)

        a = Album("युगें अठ्ठावीस", 1, covers[4])
        albumList!!.add(a)

        a = Album("येई हो विठ्ठले माझे माऊली ये", 11, covers[5])
        albumList!!.add(a)

        a = Album("जय गणेश जय गणेश देवा", 11, covers[6])
        albumList!!.add(a)

        a = Album("ओम जय जगदीश हरे", 17, covers[7])
        albumList!!.add(a)
        a = Album("श्रीज्ञानदेवाची आरती ज्ञानराजा", 17, covers[8])
        albumList!!.add(a)
        a = Album("घालीन लोटांगण", 17, covers[9])
        albumList!!.add(a)
        a = Album("श्री दत्त आरती", 17, covers[10])
        albumList!!.add(a)
        a = Album("जय अम्बे गौरी मैया जय श्यामा गौरी", 17, covers[11])
        albumList!!.add(a)
        a = Album("प्रार्थना", 17, covers[12])
        albumList!!.add(a)
        a = Album("पपुष्पांजली", 17, covers[13])
        albumList!!.add(a)
        adapter!!.notifyDataSetChanged()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@written_aarti_hindi, MainActivity::class.java)
        startActivity(intent)


        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this@written_aarti_hindi, MainActivity::class.java)
        startActivity(intent)
    }


}

