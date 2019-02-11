package com.example.android.booklist;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Typeface m=Typeface.createFromAsset(getAssets(),"Font/details.ttf");
        Typeface t=Typeface.createFromAsset(getAssets(),"Font/name.ttf");

        TextView Movie=(TextView)findViewById(R.id.movie);
        TextView Plot=(TextView)findViewById(R.id.plot);
        TextView Release=(TextView)findViewById(R.id.rel);
        Movie.setTypeface(m);
        Plot.setTypeface(m);
        Release.setTypeface(m);
        String date="",sum="",link="",tit=" ",poster=" ";
        Intent i = getIntent();
        date= i.getStringExtra("date");
        sum= i.getStringExtra("summary");
        link=i.getStringExtra("image");
        tit=i.getStringExtra("title");
        poster=i.getStringExtra("poster");
        ImageView img=(ImageView)findViewById(R.id.poster);
        Picasso.get().load(link).into(img);
        ImageView img1=(ImageView)findViewById(R.id.img);
        Picasso.get().load(poster).into(img1);

        TextView summary=(TextView)findViewById(R.id.sum);
        summary.setText(sum);

        TextView Date=(TextView)findViewById(R.id.date);
        Date.setText(date);
        TextView Adult=(TextView)findViewById(R.id.adult);
        Adult.setTypeface(m);
        Adult.setText(tit);
    }
}
