package digispark.tech.ganapatiaarti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity implements View.OnClickListener {
    private Button btn_feedback;
    private EditText etMail_feedback_message;
    private String emailMessege;
    private final String TAG = "FeedbackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("FeedBack");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etMail_feedback_message = (EditText) findViewById(R.id.etMail_feedback_message);

        btn_feedback = (Button) findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v == btn_feedback)
        {
            emailMessege = etMail_feedback_message.getText().toString();

            if (emailMessege.equalsIgnoreCase(""))
            {
                Toast.makeText(this, "Please Enter Feedback", Toast.LENGTH_SHORT).show();
            }
            else
            {
                sendFeedback();
            }
        }
    }

    private void sendFeedback()
    {
        final Intent feedbckIntent = new Intent(android.content.Intent.ACTION_SEND);
        feedbckIntent.setType("text/html");
        feedbckIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
        feedbckIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
        feedbckIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailMessege);
        startActivity(Intent.createChooser(feedbckIntent, getString(R.string.title_send_feedback)));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
