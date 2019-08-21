package digispark.tech.ganapatiaarti

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.ads.AdView

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils

class MusicPlayerActivity : Activity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener, MediaPlayer.OnCompletionListener {

    private var tvSongTitle: TextView? = null
    private var btnPlay: ImageButton? = null
    private var btnForward: ImageButton? = null
    private var btnBackward: ImageButton? = null
    private var btnNext: ImageButton? = null
    private var btnPrevious: ImageButton? = null
    private var btnRepeat: ImageButton? = null
    private var songProgressBar: SeekBar? = null
    private var songCurrentDurationLabel: TextView? = null
    private var songTotalDurationLabel: TextView? = null
    private var utils: Utilities? = null

    private val seekForwardTime = 5000 // 5000 milliseconds
    private val seekBackwardTime = 5000

    private var isRepeat = false
    internal var songIndex: String? = null
    internal var indexOfSong: Int = 0
    internal var img: ImageView? = null
    private val mHandler = Handler()
    private var adView: AdView? = null

    private val mUpdateTimeTask = object : Runnable {
        override fun run() {
            if (mp != null) {
                val totalDuration = mp!!.duration.toLong()
                val currentDuration = mp!!.currentPosition.toLong()

                // Displaying Total Duration time
                /*Log.d(TAG,"totalDuration_val " + utils.milliSecondsToTimer(totalDuration));
                songTotalDurationLabel.setText(utils.milliSecondsToTimer(totalDuration));*/

                // Displaying time completed playing
                Log.d(TAG, "currentDuration_val " + utils!!.milliSecondsToTimer(currentDuration))
                songCurrentDurationLabel!!.text = utils!!.milliSecondsToTimer(currentDuration)

                val progress = utils!!.getProgressPercentage(currentDuration, totalDuration)
                //Log.d("Progress", ""+progress);
                songProgressBar!!.progress = progress

                // Running this thread after 100 milliseconds
                mHandler.postDelayed(this, 100)
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        adView = findViewById(R.id.adView)
        UserInterfaceUtils.showBannerAd(adView)

        val intent = intent
        val intValue = intent.getIntExtra("songindex", 0)
        tvSongTitle = findViewById<View>(R.id.tvSongTitle) as TextView
        songTotalDurationLabel = findViewById<View>(R.id.songTotalDurationLabel) as TextView
        songCurrentDurationLabel = findViewById<View>(R.id.songCurrentDurationLabel) as TextView
        utils = Utilities()
        img = findViewById<View>(R.id.img) as ImageView

        indexOfSong = intValue
        //  indexOfSong = Integer.parseInt("0");
        setSongTitle(indexOfSong)
        playSongIndex(indexOfSong)
        btnPlay = findViewById<View>(R.id.btnPlay) as ImageButton
        btnPlay!!.setOnClickListener(this)

        btnForward = findViewById<View>(R.id.btnForward) as ImageButton
        btnForward!!.setOnClickListener(this)

        btnBackward = findViewById<View>(R.id.btnBackward) as ImageButton
        btnBackward!!.setOnClickListener(this)

        btnNext = findViewById<View>(R.id.btnNext) as ImageButton
        btnNext!!.setOnClickListener(this)

        btnPrevious = findViewById<View>(R.id.btnPrevious) as ImageButton
        btnPrevious!!.setOnClickListener(this)

        btnRepeat = findViewById<View>(R.id.btnRepeat) as ImageButton
        btnRepeat!!.setOnClickListener(this)

        songProgressBar = findViewById<View>(R.id.songProgressBar) as SeekBar
        songProgressBar!!.setOnSeekBarChangeListener(this)

        mUpdateTimeTask.run()
    }

    fun updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100)
    }


    override fun onClick(v: View) {
        if (v === btnPlay) {
            if (adView != null)
                UserInterfaceUtils.showBannerAd(adView)

            if (mp!!.isPlaying) {
                if (mp != null) {
                    mp!!.pause()
                    btnPlay!!.setImageResource(R.drawable.btn_play)
                }
            } else {
                if (mp != null) {
                    mp!!.start()
                    btnPlay!!.setImageResource(R.drawable.btn_pause)
                }
            }
        }

        if (v === btnForward) {
            val currentPosition = mp!!.currentPosition
            // check if seekForward time is lesser than song duration
            if (currentPosition + seekForwardTime <= mp!!.duration) {
                // forward song
                mp!!.seekTo(currentPosition + seekForwardTime)
            } else {
                // forward to end position
                mp!!.seekTo(mp!!.duration)
            }
        }
        if (v === btnBackward) {
            val currentPosition = mp!!.currentPosition
            // check if seekBackward time is greater than 0 sec
            if (currentPosition - seekBackwardTime >= 0) {
                // forward song
                mp!!.seekTo(currentPosition - seekBackwardTime)
            } else {
                // backward to starting position
                mp!!.seekTo(0)
            }
        }
        if (v === btnNext) {
            if (adView != null)
                UserInterfaceUtils.showBannerAd(adView)
            if (mp != null)
                mp!!.stop()

            Log.d("btnNext", "indexOfSong_val $indexOfSong")

            if (indexOfSong < 10) {
                val addSongIndex = 1
                val nextValue = addSongIndex + indexOfSong
                Log.d("btnNext", "nextValue_val $nextValue")

                indexOfSong++
                Log.d("btnNext", "indexOfSong_new_val $indexOfSong")

                setSongTitle(nextValue)
                playSongIndex(nextValue)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
            } else {
                setSongTitle(0)
                playSongIndex(0)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
                indexOfSong = 0
            }
        }
        if (v === btnPrevious) {
            if (adView != null)
                UserInterfaceUtils.showBannerAd(adView)
            if (mp != null)
                mp!!.stop()

            Log.d("btnPrevious", "indexOfSong_val $indexOfSong")

            if (indexOfSong > 0) {
                val subtractSongIndex = 1
                val preValue = indexOfSong - subtractSongIndex
                Log.d("btnPrevious", "nextValue_val $preValue")

                indexOfSong--
                Log.d("btnPrevious", "indexOfSong_new_val $indexOfSong")

                setSongTitle(preValue)
                playSongIndex(preValue)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
            } else {
                setSongTitle(15)
                playSongIndex(15)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
                indexOfSong = 15
            }
        }
        if (v === btnRepeat) {
            if (isRepeat) {
                isRepeat = false
                Toast.makeText(applicationContext, "Repeat is OFF", Toast.LENGTH_SHORT).show()
                btnRepeat!!.setImageResource(R.drawable.btn_repeat)
            } else {
                // make repeat to true
                isRepeat = true
                Toast.makeText(applicationContext, "Repeat is ON", Toast.LENGTH_SHORT).show()

                btnRepeat!!.setImageResource(R.drawable.img_btn_repeat_pressed)
            }
            //Toast.makeText(this, "We will add this feature in next version", Toast.LENGTH_SHORT).show();

        }
    }

    fun setSongTitle(titleIndex: Int) {
        if (titleIndex == 0) {

            img?.setImageResource(R.drawable.album1)
            tvSongTitle!!.text = "Ekadantaya Vakratundaya"

        }
        if (titleIndex == 1) {
            img?.setImageResource(R.drawable.album2)
            tvSongTitle!!.text = "Sindoor Lal Chadayo"
        }
        if (titleIndex == 2) {
            img?.setImageResource(R.drawable.album3)
            tvSongTitle!!.text = "Sukhkarta Dukhharta "
        }
        if (titleIndex == 3) {
            img?.setImageResource(R.drawable.album4)
            tvSongTitle!!.text = "Lavthavti Vikrala Shankar"
        }
        if (titleIndex == 4) {
            img?.setImageResource(R.drawable.album_2)
            tvSongTitle!!.text = "Durge Durghat Bhari"
        }
        if (titleIndex == 5) {
            img?.setImageResource(R.drawable.album6)
            tvSongTitle!!.text = "Yuge Atthavis"
        }
        if (titleIndex == 6) {
            img?.setImageResource(R.drawable.album5)
            tvSongTitle!!.text = "Yei Oh Vitthale"
        }
        if (titleIndex == 7) {
            img?.setImageResource(R.drawable.album1)
            tvSongTitle!!.text = "Tu Sukhkarta"
        }
        if (titleIndex == 8) {
            img?.setImageResource(R.drawable.img11)
            tvSongTitle!!.text = "Jai Ganesh"
        }
        if (titleIndex == 9) {
            img?.setImageResource(R.drawable.album7)
            tvSongTitle!!.text = "Om Jai Jagdish Hare"
        }
    }

    fun playSongIndex(index: Int) {
        if (mp != null) {
            mp!!.stop()
            mp!!.release()
        }

        if (index == 0) {
            mp = MediaPlayer.create(this, R.raw.ekadantaya_vakratundaya)
        }
        if (index == 1) {
            mp = MediaPlayer.create(this, R.raw.sindoor_lal_chadayo)
        }
        if (index == 2) {
            mp = MediaPlayer.create(this, R.raw.sukhkarta_dukhharta)
        }
        if (index == 3) {
            mp = MediaPlayer.create(this, R.raw.lavthavti_vikrala)
        }
        if (index == 4) {
            mp = MediaPlayer.create(this, R.raw.durge_durghat_bhari)
        }
        if (index == 5) {
            mp = MediaPlayer.create(this, R.raw.yuge_atthavis)
        }
        if (index == 6) {
            mp = MediaPlayer.create(this, R.raw.yei_oh_vitthale)
        }
        if (index == 7) {
            mp = MediaPlayer.create(this, R.raw.tu_sukhkarta_tu_dukhharta)
        }

        if (index == 8) {
            mp = MediaPlayer.create(this, R.raw.jai_ganesh_jai_ganesh)
        }
        if (index == 9) {
            mp = MediaPlayer.create(this, R.raw.om_ai_jagdish_hare)
        }

        if (mp != null) {
            mp!!.start()
            mp!!.setOnCompletionListener(this)
            val totalDuration = mp!!.duration.toLong()
            songTotalDurationLabel!!.text = utils!!.milliSecondsToTimer(totalDuration)
            Log.d(TAG, "total_val " + utils!!.milliSecondsToTimer(totalDuration))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask)
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask)
        val totalDuration = mp!!.duration
        val currentPosition = utils!!.progressToTimer(seekBar.progress, totalDuration)

        // forward or backward to certain seconds
        mp!!.seekTo(currentPosition)
        // update timer progress again
        updateProgressBar()
    }

    override fun onCompletion(mp: MediaPlayer) {
        Log.d("onCompletion", "Completed_Song_index $indexOfSong")

        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on play same song again
            playSongIndex(indexOfSong)
        } else {
            if (indexOfSong < 10) {
                val addSongIndex = 1
                val nextValue = addSongIndex + indexOfSong
                Log.d("onCompletion", "nextValue_val $nextValue")

                indexOfSong++
                Log.d("onCompletion", "indexOfSong_new_val $indexOfSong")

                setSongTitle(nextValue)
                playSongIndex(nextValue)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
            } else {
                setSongTitle(0)
                playSongIndex(0)
                btnPlay!!.setImageResource(R.drawable.btn_pause)
                indexOfSong = 0
            }
        }
    }

    companion object {

        var mp: MediaPlayer? = null
        private val TAG = "MainActivity"
    }


}
