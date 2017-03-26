package com.pchef.cc.personalchef;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    EditText fftext;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        fftext=(EditText) findViewById(R.id.edt_feedback);

        send=(Button) findViewById(R.id.sendFeedback);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    void sendFeedback()
    {
        String feedback;

        String subject = "Feedback";
        feedback=fftext.getText().toString();
        if(!feedback.trim().isEmpty())
        {

            Log.i("Send email", "");
            String TO = "ccampus24@gmail.com";

            Intent send = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode(TO) +
                    "?subject=" + Uri.encode(subject) +
                    "&body=" + Uri.encode(feedback);
            Uri uri = Uri.parse(uriText);

            send.setData(uri);
            startActivity(Intent.createChooser(send, "Send mail..."));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please Enter Text in Feedback Details", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            startActivity(new Intent(Feedback.this,Feedback.class));
            return true;
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(Feedback.this,AboutUs.class));
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            Intent intent=new Intent(Feedback.this,Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
