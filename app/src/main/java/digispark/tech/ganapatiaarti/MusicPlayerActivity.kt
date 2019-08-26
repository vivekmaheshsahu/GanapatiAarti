package digispark.tech.ganapatiaarti

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.ads.AdView
import digispark.tech.ganapatiaarti.constants.Constant

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class MusicPlayerActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener, MediaPlayer.OnCompletionListener {

    private var mAdView: AdView? = null
    private var btnPlay: ImageButton? = null
    private var btnForward: ImageButton? = null
    private var btnBackward: ImageButton? = null
    private var btnNext: ImageButton? = null
    private var btnPrevious: ImageButton? = null
    private var songProgressBar: SeekBar? = null
    private var songCurrentDurationLabel: TextView? = null
    private var songTotalDurationLabel: TextView? = null
    private var utils: Utilities? = null
    private val seekForwardTime = 5000 // 5000 milliseconds
    private val seekBackwardTime = 5000
    internal var indexOfSong: Int = 0
    private val mHandler = Handler()
    private var isActivityVisible: Boolean = false
    private var img: ImageView? = null
    private var songListSize: Int = 9

    private val mUpdateTimeTask = object : Runnable {
        override fun run() {
            if (mp != null) {
                val totalDuration = mp?.duration?.toLong()
                val currentDuration = mp?.currentPosition?.toLong()

                // Displaying Total Duration time
                /*Log.d(TAG,"totalDuration_val " + utils.milliSecondsToTimer(totalDuration));
            songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));*/

                // Displaying time completed playing
                Log.d(TAG, "Song current Duration: " + currentDuration?.let { utils?.milliSecondsToTimer(it) })
                songCurrentDurationLabel?.text = currentDuration?.let { utils?.milliSecondsToTimer(it) }

                if (isActivityVisible) {
                    if (mp != null) {
                        if (mp?.isPlaying!!) {
                            btnPlay?.setImageResource(R.drawable.btn_pause)
                        } else
                            btnPlay?.setImageResource(R.drawable.btn_play)
                    }
                }

                val progress = currentDuration?.let { totalDuration?.let { it1 -> utils?.getProgressPercentage(it, it1) } }
                //Log.d("Progress", ""+progress);
                if (progress != null) {
                    songProgressBar?.progress = progress
                }

                // Running this thread after 1000 milliseconds
                mHandler.postDelayed(this, 1000)
            } else {
                Log.d(TAG, "finish_called")
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializer()
    }

    private fun initializer(){
        mAdView = findViewById(R.id.adView)

        songTotalDurationLabel = findViewById(R.id.songTotalDurationLabel)
        songCurrentDurationLabel = findViewById(R.id.songCurrentDurationLabel)
        btnPlay = findViewById(R.id.btnPlay)
        btnPlay?.setOnClickListener(this)
        btnForward = findViewById(R.id.btnForward)
        btnForward?.setOnClickListener(this)
        btnBackward = findViewById(R.id.btnBackward)
        btnBackward?.setOnClickListener(this)
        btnNext = findViewById(R.id.btnNext)
        btnNext?.setOnClickListener(this)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnPrevious?.setOnClickListener(this)
        img = findViewById(R.id.img)
        songProgressBar = findViewById(R.id.songProgressBar)
        songProgressBar?.progress = 0
        songProgressBar?.setOnSeekBarChangeListener(this)

        val mIntent = intent
        if (mIntent != null) {
            indexOfSong = mIntent.getIntExtra("songindex", 0)
        }
        Log.d(TAG, "song_index $indexOfSong")
        utils = Utilities()
        playSongIndex(indexOfSong)
        mUpdateTimeTask.run()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart_called")
        isActivityVisible = true
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause_called")
        isActivityVisible = false
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume_called")
        UserInterfaceUtils.loadAd(mAdView)
        isActivityVisible = true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy_called")
        isActivityVisible = false
    }

    fun updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 1000)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnPlay -> {
                UserInterfaceUtils.loadAd(mAdView)
                if (mp?.isPlaying!!) {
                    if (mp != null) {
                        mp?.pause()
                        btnPlay?.setImageResource(R.drawable.btn_play)
                    }
                } else {
                    if (mp != null) {
                        mp?.start()
                        btnPlay?.setImageResource(R.drawable.btn_pause)
                    }
                }
            }

            R.id.btnForward -> {
                val currentPosition = mp?.currentPosition
                // check if seekForward time is lesser than song duration
                if (currentPosition != null) {
                    if (currentPosition + seekForwardTime <= mp!!.duration) {
                        // forward song
                        mp?.seekTo(currentPosition + seekForwardTime)
                    } else {
                        // forward to end position
                        mp?.duration?.let { mp?.seekTo(it) }
                    }
                }
            }

            R.id.btnBackward -> {
                val currentPosition1 = mp?.currentPosition
                // check if seekBackward time is greater than 0 sec
                if (currentPosition1 != null) {
                    if (currentPosition1 - seekBackwardTime >= 0) {
                        // forward song
                        mp?.seekTo(currentPosition1 - seekBackwardTime)
                    } else {
                        // backward to starting position
                        mp?.seekTo(0)
                    }
                }
            }

            R.id.btnNext -> {
                if (mp != null)
                    mp?.stop()
                UserInterfaceUtils.loadAd(mAdView)
                playNextSong()
            }

            R.id.btnPrevious -> {
                if (mp != null)
                    mp?.stop()
                UserInterfaceUtils.loadAd(mAdView)
                playPrevSong()
            }
        }
    }

    fun  playNextSong() {
        if (indexOfSong < songListSize) {
            val nextValue = 1 + indexOfSong
            indexOfSong++
            Log.d(TAG, "nextSongIndex: $nextValue")
            playSongIndex(nextValue)
            btnPlay?.setImageResource(R.drawable.btn_pause)
        } else {
            playSongIndex(0)
            btnPlay?.setImageResource(R.drawable.btn_pause)
            indexOfSong = 0
        }
    }

    fun playPrevSong() {
        if (indexOfSong > 0) {
            val preValue = indexOfSong - 1
            indexOfSong--
            Log.d(TAG, "prevSongIndex: $preValue")
            playSongIndex(preValue)
            btnPlay?.setImageResource(R.drawable.btn_pause)
        } else {
            playSongIndex(songListSize)
            btnPlay?.setImageResource(R.drawable.btn_pause)
            indexOfSong = songListSize
        }
    }

    fun playSongIndex(index: Int) {
        if (mp != null) {
            mp?.stop()
            mp?.release()
        }

        when (index) {
            0 -> {
                supportActionBar?.title = resources.getString(R.string.ekadantaya_vakratundaya)
                img?.setImageResource(R.drawable.album1)
                mp = MediaPlayer.create(this, R.raw.ekadantaya_vakratundaya)
            }
            1 -> {
                supportActionBar?.title = resources.getString(R.string.sindoor_lal_chadayo)
                img?.setImageResource(R.drawable.album2)
                mp = MediaPlayer.create(this, R.raw.sindoor_lal_chadayo)
            }
            2 -> {
                supportActionBar?.title = resources.getString(R.string.sukhkarta_dukhharta)
                img?.setImageResource(R.drawable.album3)
                mp = MediaPlayer.create(this, R.raw.sukhkarta_dukhharta)
            }
            3 -> {
                supportActionBar?.title = resources.getString(R.string.lavthavti_vikrala)
                img?.setImageResource(R.drawable.album4)
                mp = MediaPlayer.create(this, R.raw.lavthavti_vikrala)
            }
            4 -> {
                supportActionBar?.title = resources.getString(R.string.durge_durgat_bhari)
                img?.setImageResource(R.drawable.album_2)
                mp = MediaPlayer.create(this, R.raw.durge_durghat_bhari)
            }
            5 -> {
                supportActionBar?.title = resources.getString(R.string.yuge_atthavis)
                img?.setImageResource(R.drawable.album6)
                mp = MediaPlayer.create(this, R.raw.yuge_atthavis)
            }
            6 -> {
                supportActionBar?.title = resources.getString(R.string.yei_o_vitthale)
                img?.setImageResource(R.drawable.album5)
                mp = MediaPlayer.create(this, R.raw.yei_oh_vitthale)
            }
            7 -> {
                supportActionBar?.title = resources.getString(R.string.tu_sukhkarta)
                img?.setImageResource(R.drawable.album1)
                mp = MediaPlayer.create(this, R.raw.tu_sukhkarta_tu_dukhharta)
            }
            8 -> {
                supportActionBar?.title = resources.getString(R.string.jai_ganesh)
                img?.setImageResource(R.drawable.img11)
                mp = MediaPlayer.create(this, R.raw.jai_ganesh_jai_ganesh)
            }
            9 -> {
                supportActionBar?.title = resources.getString(R.string.om_jai_jagadish)
                img?.setImageResource(R.drawable.album7)
                mp = MediaPlayer.create(this, R.raw.om_ai_jagdish_hare)
            }
        }

        if (mp != null) {
            mp?.start()
            mp?.setOnCompletionListener(this)
            val totalDuration = mp?.duration?.toLong()

            songTotalDurationLabel?.text = totalDuration?.let { utils?.milliSecondsToTimer(it) }
            Log.d(TAG, "Song_total_duration " + totalDuration?.let { utils?.milliSecondsToTimer(it) })
        }
    }


    override fun onBackPressed() {
        backPressed()
    }

    fun backPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        Constant.NOW_PLAYING_SONG_NAME = supportActionBar?.title.toString()
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                backPressed()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        Log.d(TAG, "onStartTrackingTouch")
        mHandler.removeCallbacks(mUpdateTimeTask)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        Log.d(TAG, "onStopTrackingTouch")
        mHandler.removeCallbacks(mUpdateTimeTask)
        val totalDuration = mp?.duration
        val currentPosition = totalDuration?.let { utils?.progressToTimer(seekBar.progress, it) }
        // forward or backward to certain seconds
        if (currentPosition != null) {
            mp?.seekTo(currentPosition)
        }
        // update timer progress again
        updateProgressBar()
    }

    override fun onCompletion(mp: MediaPlayer) {
        Log.d(TAG, "Completed_Song_index $indexOfSong")
        playNextSong()
    }

    companion object {
        var mp: MediaPlayer? = null
        private val TAG = "MusicPlayerActivity"
    }


}
