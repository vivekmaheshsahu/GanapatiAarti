package digispark.tech.ganapatiaarti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdView;

import digispark.tech.ganapatiaarti.utils.UserInterfaceUtils;

public class pune_mandle_detail extends AppCompatActivity {
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pune_mandle_detail);
        setTitle("Pune Mandals");
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adView = findViewById(R.id.adView);
        UserInterfaceUtils.showBannerAd(adView);

        Intent intent = getIntent();
        int intValue = intent.getIntExtra("songindex", 0);

        TextView t1,t2;
        ImageView im1;
        im1=(ImageView)(findViewById(R.id.img1)) ;
        t1 =(TextView)(findViewById(R.id.txt));
        t2 = (TextView)(findViewById(R.id.t1));
        if(intValue ==0)
        {
            im1.setImageResource(R.drawable.g23);

            t2.setText("Shrimant Dagdusheth Halwai Ganapati");
            t1.setText("Dagdusheth Halwai Ganesha has a history of more than 100 years and is participating in the Ganeshotsav from 1893");
        }
        if(intValue ==1)
        {
            im1.setImageResource(R.drawable.g24);
            t2.setText("Rajarshi Shahu Chowk Ganeshotsav Mandal");
            t1.setText("Ganesh mandal in Pune is famous of Hindu temples in India during Ganesh Chaturthi.");
        }
        if(intValue ==2)
        {
            im1.setImageResource(R.drawable.g25);
            t2.setText("Tambadi Jogeshwari Ganapati Mandal");
            t1.setText("This Ganesha Mandal has been participating in Ganeshotsav from 1893 and the Jogeshwari Ganpati is one of the most famous Sarvajanik Ganesha Festival.");
        }
        if(intValue ==3)
        {
            im1.setImageResource(R.drawable.g26);
            t2.setText("Hutatma Babu Genu Ganesh Mandal ");
            t1.setText("This Sarvajanik Mandal is noted for its huge and beautiful Ganesh Pandals");
        }
        if(intValue ==4)
        {
            im1.setImageResource(R.drawable.g27);
            t2.setText("Mandai Ganeshotsav Mandal ");
            t1.setText("This popular Sarvajanik Ganesh mandal is noted for its decorations and also for involvement in social causes. It is also runs a cooperative credit society.");
        }
        if(intValue ==5)
        {
            im1.setImageResource(R.drawable.g28);
            t2.setText("Tulshibag Sarvajanik Ganeshotsav ");
            t1.setText("This popular Ganesh Mandal at Budhwar Peth attracts thousands of devotees.  14-feet Ganesh idol of Tulshibag is popular among devotees.");
        }
        if(intValue ==6)
        {
            im1.setImageResource(R.drawable.g29);
            t2.setText("Guruji Talim Ganapati ");
            t1.setText("Established in 1887, Guruji Talim Ganapati is popular for its decorations and replicas of temples and popular monuments. One of the oldest mandals in Pune city.");
        }
        if(intValue ==7)
        {
            im1.setImageResource(R.drawable.g30);
            t2.setText("Hatti Ganpati Sarvajanik ");
            t1.setText("This pandal is famous for its decorations. Pujas and rituals are attended by dedicated devotees every year.");
        }

        else
        {

        }



    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
