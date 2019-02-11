package com.example.android.booklist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.booklist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t=(TextView)findViewById(R.id.text);
        TextView t1=(TextView)findViewById(R.id.footer);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"Font/Vintage.ttf");
        Typeface myCustomFont1=Typeface.createFromAsset(getAssets(),"Font/details.ttf");
        t.setTypeface(myCustomFont);
        t1.setTypeface(myCustomFont1);
        Button b=(Button)findViewById(R.id.booksbut);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent=new Intent(MainActivity.this,BookActivity.class);
                startActivity(bookIntent);
            }
        });
        Button m=(Button)findViewById(R.id.moviesbut);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent=new Intent(MainActivity.this,MovieActivity.class);
                startActivity(bookIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_save:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT,"Problem faced with Bookie");
                intent.putExtra(Intent.EXTRA_TEXT,"Type your problem");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"gaurav.llh03@gmail.com"});


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                startActivity(Intent.createChooser(intent, "Send Email"));
                return true;
            /*case R.id.action_help:
                Uri BUri = Uri.parse("https://support.google.com/books/partner/?hl=en#topic=3424344");

                // Create a new intent to view the earthquake URI
                Intent webIntent = new Intent(Intent.ACTION_VIEW, BUri);

                // Send the intent to launch a new activity
                startActivity(webIntent);*/
        }
        /*if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
