package digispark.tech.ganapatiaarti;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayerActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, MediaPlayer.OnCompletionListener {

    private TextView tvSongTitle;
    private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnRepeat;
    private SeekBar songProgressBar;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    private Utilities utils;

    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000;

    private boolean isRepeat = false;

    public static MediaPlayer mp;
    String songIndex;
    private static final String TAG = "MainActivity";


    int indexOfSong;
    ImageView img;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("songindex", 0);
        tvSongTitle = (TextView) findViewById(R.id.tvSongTitle);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        utils = new Utilities();
        img = (ImageView)(findViewById(R.id.img));

        indexOfSong = intValue;
      //  indexOfSong = Integer.parseInt("0");
        setSongTitle(indexOfSong);
        playSongIndex(indexOfSong);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnForward.setOnClickListener(this);

        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnBackward.setOnClickListener(this);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(this);

        btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        btnRepeat.setOnClickListener(this);

        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songProgressBar.setOnSeekBarChangeListener(this);

        mUpdateTimeTask.run();
    }

    private Runnable mUpdateTimeTask = new Runnable()
    {
        public void run()
        {
            if (mp != null){
                long totalDuration = mp.getDuration();
                long currentDuration = mp.getCurrentPosition();

                // Displaying Total Duration time
                /*Log.d(TAG,"totalDuration_val " + utils.milliSecondsToTimer(totalDuration));
                songTotalDurationLabel.setText(utils.milliSecondsToTimer(totalDuration));*/

                // Displaying time completed playing
                Log.d(TAG, "currentDuration_val " + utils.milliSecondsToTimer(currentDuration));
                songCurrentDurationLabel.setText(utils.milliSecondsToTimer(currentDuration));

                int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
                //Log.d("Progress", ""+progress);
                songProgressBar.setProgress(progress);

                // Running this thread after 100 milliseconds
                mHandler.postDelayed(this, 100);
            }
            else
            {
                finish();
            }
        }
    };

    public void updateProgressBar()
    {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }


    public void onClick(View v)
    {
        if (v == btnPlay)
        {
            if(mp.isPlaying())
            {
                if(mp != null)
                {
                    mp.pause();
                    btnPlay.setImageResource(R.drawable.btn_play);
                }
            }
            else
            {
                if (mp != null)
                {
                    mp.start();
                    btnPlay.setImageResource(R.drawable.btn_pause);
                }
            }
        }

        if (v == btnForward)
        {
            int currentPosition = mp.getCurrentPosition();
            // check if seekForward time is lesser than song duration
            if(currentPosition + seekForwardTime <= mp.getDuration())
            {
                // forward song
                mp.seekTo(currentPosition + seekForwardTime);
            }
            else
            {
                // forward to end position
                mp.seekTo(mp.getDuration());
            }
        }
        if (v == btnBackward)
        {
            int currentPosition = mp.getCurrentPosition();
            // check if seekBackward time is greater than 0 sec
            if(currentPosition - seekBackwardTime >= 0)
            {
                // forward song
                mp.seekTo(currentPosition - seekBackwardTime);
            }
            else
            {
                // backward to starting position
                mp.seekTo(0);
            }
        }
        if (v == btnNext)
        {
            if (mp != null)
                mp.stop();

            Log.d("btnNext","indexOfSong_val " + indexOfSong);

            if (indexOfSong < 10)
            {
                int addSongIndex = 1;
                int nextValue = addSongIndex + indexOfSong;
                Log.d("btnNext", "nextValue_val " + nextValue );

                indexOfSong++;
                Log.d("btnNext","indexOfSong_new_val " + indexOfSong);

                setSongTitle(nextValue);
                playSongIndex(nextValue);
                btnPlay.setImageResource(R.drawable.btn_pause);
            }
            else
            {
                setSongTitle(0);
                playSongIndex(0);
                btnPlay.setImageResource(R.drawable.btn_pause);
                indexOfSong = 0;
            }
        }
        if (v == btnPrevious)
        {
            if (mp != null)
                mp.stop();

            Log.d("btnPrevious","indexOfSong_val " + indexOfSong);

            if (indexOfSong > 0)
            {
                int subtractSongIndex = 1;
                int preValue = indexOfSong - subtractSongIndex;
                Log.d("btnPrevious", "nextValue_val " + preValue );

                indexOfSong--;
                Log.d("btnPrevious","indexOfSong_new_val " + indexOfSong);

                setSongTitle(preValue);
                playSongIndex(preValue);
                btnPlay.setImageResource(R.drawable.btn_pause);
            }
            else
            {
                setSongTitle(15);
                playSongIndex(15);
                btnPlay.setImageResource(R.drawable.btn_pause);
                indexOfSong = 15;
            }
        }
        if (v == btnRepeat)
        {
            if(isRepeat)
            {
                isRepeat = false;
                Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
                btnRepeat.setImageResource(R.drawable.btn_repeat);
            }
            else
            {
                // make repeat to true
                isRepeat = true;
                Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();

                btnRepeat.setImageResource(R.drawable.img_btn_repeat_pressed);
            }
            //Toast.makeText(this, "We will add this feature in next version", Toast.LENGTH_SHORT).show();

        }
    }

    public void setSongTitle(int titleIndex)
    {
        if (titleIndex == 0)
        {

            img.setImageResource(R.drawable.album1);
            tvSongTitle.setText("Ekadantaya Vakratundaya");

        }
        if (titleIndex == 1)
        {
            img.setImageResource(R.drawable.album2);
            tvSongTitle.setText("Sindoor Lal Chadayo");
        }
        if (titleIndex == 2)
        {
            img.setImageResource(R.drawable.album3);
            tvSongTitle.setText("Sukhkarta Dukhharta ");
        }
        if (titleIndex == 3)
        {
            img.setImageResource(R.drawable.album4);
            tvSongTitle.setText("Lavthavti Vikrala Shankar");
        }
        if (titleIndex == 4)
        {
            img.setImageResource(R.drawable.album_2);
            tvSongTitle.setText("Durge Durghat Bhari");
        }
        if (titleIndex == 5)
        {
            img.setImageResource(R.drawable.album6);
            tvSongTitle.setText("Yuge Atthavis");
        }
        if (titleIndex == 6)
        {
            img.setImageResource(R.drawable.album5);
            tvSongTitle.setText("Yei Oh Vitthale");
        }
        if (titleIndex == 7)
        {
            img.setImageResource(R.drawable.album1);
            tvSongTitle.setText("Tu Sukhkarta");
        }
        if (titleIndex == 8)
        {
            img.setImageResource(R.drawable.img11);
            tvSongTitle.setText("Jai Ganesh");
        }
        if (titleIndex == 9)
        {
            img.setImageResource(R.drawable.album7);
            tvSongTitle.setText("Om Jai Jagdish Hare");
        }
    }

    public void playSongIndex(int index)
    {
        if (mp != null){
            mp.stop();
            mp.release();
        }

        if (index == 0)
        {
            mp = MediaPlayer.create(this, R.raw.ekadantaya_vakratundaya);
        }
        if (index == 1)
        {
            mp = MediaPlayer.create(this, R.raw.sindoor_lal_chadayo);
        }
        if (index == 2)
        {
            mp = MediaPlayer.create(this, R.raw.sukhkarta_dukhharta);
        }
        if (index == 3)
        {
            mp = MediaPlayer.create(this, R.raw.lavthavti_vikrala);
        }
        if (index == 4)
        {
            mp = MediaPlayer.create(this, R.raw.durge_durghat_bhari);
        }
        if (index == 5)
        {
            mp = MediaPlayer.create(this, R.raw.yuge_atthavis);
        }
        if (index == 6)
        {
            mp = MediaPlayer.create(this, R.raw.yei_oh_vitthale);
        }
        if (index == 7)
        {
            mp = MediaPlayer.create(this, R.raw.tu_sukhkarta_tu_dukhharta);
        }

        if (index == 8)
        {
            mp = MediaPlayer.create(this, R.raw.jai_ganesh_jai_ganesh);
        }
        if (index == 9)
        {
            mp = MediaPlayer.create(this, R.raw.om_ai_jagdish_hare);
        }

        if (mp != null){
            mp.start();
            mp.setOnCompletionListener(this);
            long totalDuration = mp.getDuration();
            songTotalDurationLabel.setText(utils.milliSecondsToTimer(totalDuration));
            Log.d(TAG, "total_val " + utils.milliSecondsToTimer(totalDuration));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);
        // update timer progress again
        updateProgressBar();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        Log.d("onCompletion","Completed_Song_index " + indexOfSong);

        // check for repeat is ON or OFF
        if(isRepeat)
        {
            // repeat is on play same song again
            playSongIndex(indexOfSong);
        }
        else
        {
            if (indexOfSong < 10)
            {
                int addSongIndex = 1;
                int nextValue = addSongIndex + indexOfSong;
                Log.d("onCompletion", "nextValue_val " + nextValue );

                indexOfSong++;
                Log.d("onCompletion","indexOfSong_new_val " + indexOfSong);

                setSongTitle(nextValue);
                playSongIndex(nextValue);
                btnPlay.setImageResource(R.drawable.btn_pause);
            }
            else
            {
                setSongTitle(0);
                playSongIndex(0);
                btnPlay.setImageResource(R.drawable.btn_pause);
                indexOfSong = 0;
            }
        }
    }


}
