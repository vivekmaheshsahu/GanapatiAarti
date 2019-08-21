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

class Written_aarti_english : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private var adapter: AlbumsAdapter_written_english? = null
    private var albumList: MutableList<Album>? = null
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_written_aarti_english)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.showBannerAd(adView)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        albumList = ArrayList()
        adapter = AlbumsAdapter_written_english(albumList!!)

        var mlayoutmanager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        mlayoutmanager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = mlayoutmanager
        recyclerView!!.adapter = adapter
        prepareAlbums()


    }

    private fun prepareAlbums() {
        val covers = intArrayOf(R.drawable.album3, R.drawable.album2, R.drawable.album4, R.drawable.album_2, R.drawable.album6, R.drawable.album5, R.drawable.img11, R.drawable.album7, R.drawable.album1, R.drawable.album3, R.drawable.album_2, R.drawable.datta, R.drawable.album11)

        var a = Album("Sukhkarta Dukhharta", 13, covers[0])
        albumList!!.add(a)

        a = Album("Shendur Lal Chadhayo", 8, covers[1])
        albumList!!.add(a)

        a = Album("Lavthavti Vikrala", 12, covers[2])
        albumList!!.add(a)

        a = Album("Durge Durghat Bhari", 14, covers[3])
        albumList!!.add(a)

        a = Album("Yuge Atthavis", 1, covers[4])
        albumList!!.add(a)

        a = Album("Yei Oh Vitthale", 11, covers[5])
        albumList!!.add(a)


        a = Album("Jai Ganesh Jai Ganesh", 11, covers[6])
        albumList!!.add(a)

        a = Album("Om Jai Jagdish Hare", 17, covers[7])
        albumList!!.add(a)

        a = Album("Shri Dynarajachi Aarti", 17, covers[8])
        albumList!!.add(a)

        a = Album("Ghalin lotangan vandin charan", 17, covers[9])
        albumList!!.add(a)
        a = Album("Jai ambe gauri maiya jai shyama gauri", 17, covers[10])
        albumList!!.add(a)
        a = Album("Shri Dattachi Aarti", 17, covers[11])
        albumList!!.add(a)
        a = Album("Mantra pushpanjali", 11, covers[12])
        albumList!!.add(a)
        adapter!!.notifyDataSetChanged()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@Written_aarti_english, MainActivity::class.java)
        startActivity(intent)

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent = Intent(this@Written_aarti_english, MainActivity::class.java)
        startActivity(intent)
    }


}

