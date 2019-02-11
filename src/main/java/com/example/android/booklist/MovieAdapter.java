package com.example.android.booklist;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private static String imagebase="https://image.tmdb.org/t/p/w500";
    private static String youtube="https://www.youtube.com/watch?v=";
    public MovieAdapter(Context context, ArrayList<Movie> pWords) {
        super(context, 0, pWords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Movie local_movie = getItem(position);
        Button b1=(Button)listItemView.findViewById(R.id.view);
        b1.setText("Trailer");
        Button b2=(Button)listItemView.findViewById(R.id.download);
        b2.setText("Details");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(local_movie.getMk()!=null && !local_movie.getMk().isEmpty()){
                String url =youtube+local_movie.getMk();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.putExtra("force_fullscreen",true);
                v.getContext().startActivity(intent);
                    Toast toast = Toast.makeText(getContext(), "Trailer loading", Toast.LENGTH_SHORT); toast.show();
                /*Uri BookUri = Uri.parse(local_book.getMlink());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, BookUri);
                getContext().startActivity(websiteIntent);*/
                   /* Intent myIntent = new Intent(getContext(),youtubeview.class);
                    myIntent.putExtra("link",url);
                    getContext().startActivity(myIntent);*/

                    }
                else
                {
                    Toast.makeText(v.getContext(), "Trailer isn't officially available ", Toast.LENGTH_LONG).show();
                }
                /*Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);*/
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),Details.class);
                myIntent.putExtra("date", local_movie.getMdate());
                myIntent.putExtra("title", local_movie.getMtitle());
                myIntent.putExtra("summary", local_movie.getMsum());
                myIntent.putExtra("image",imagebase+local_movie.getMimg());
                myIntent.putExtra("poster",imagebase+local_movie.getMposter());


                //Optional parameters
                //Optional parameters
                v.getContext().startActivity(myIntent);
            }
        });
        TextView tit = (TextView) listItemView.findViewById(R.id.title);
        tit.setText(local_movie.getMtitle());
        TextView t = (TextView) listItemView.findViewById(R.id.author);
            t.setText("Average Rating : "+local_movie.getMrating());
        ImageView img=(ImageView)listItemView.findViewById(R.id.image);
            Picasso.get().load(imagebase+local_movie.getMimg()).into(img);
        return listItemView;
    }

}