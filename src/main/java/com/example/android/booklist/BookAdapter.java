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

import com.example.android.booklist.Book;
import com.example.android.booklist.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, ArrayList<Book> pWords) {
        super(context, 0, pWords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Book local_book = getItem(position);
        Button b1=(Button)listItemView.findViewById(R.id.view);
        Button b2=(Button)listItemView.findViewById(R.id.download);
        b1.setText("View");
        b2.setText("Download");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "Preparing to View", Toast.LENGTH_SHORT); toast.show();
                /*Uri BookUri = Uri.parse(local_book.getMlink());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, BookUri);
                getContext().startActivity(websiteIntent);*/
                Intent myIntent = new Intent(getContext(),webview.class);
                myIntent.putExtra("full",local_book.getURL());
                myIntent.putExtra("down",local_book.getMisbn());
                myIntent.putExtra("text",local_book.getMtext());
                myIntent.putExtra("img",local_book.getMimg());
                myIntent.putExtra("view",local_book.getMlink());
                //Optional parameters
                getContext().startActivity(myIntent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      if(!(local_book.getMdown().isEmpty())) {
                                          Toast toast = Toast.makeText(getContext(), "Preparing to Download", Toast.LENGTH_SHORT); toast.show();
                                          Uri BookU = Uri.parse(local_book.getMdown());

                                          // Create a new intent to view the earthquake URI
                                          Intent websiteIntent = new Intent(Intent.ACTION_VIEW, BookU);

                                          // Send the intent to launch a new activity
                                          getContext().startActivity(websiteIntent);

                                      }
                                      else
                                      {
                                          Toast toast = Toast.makeText(getContext(), "Download Link not available", Toast.LENGTH_SHORT); toast.show();
                                      }
                                  }
                              });
        TextView tit = (TextView) listItemView.findViewById(R.id.title);
        tit.setText(local_book.getTitle());
        TextView t = (TextView) listItemView.findViewById(R.id.author);
        if(local_book.getMauthor()!= null && !local_book.getMauthor().isEmpty())
            t.setText("AUTHOR : "+local_book.getMauthor());
        else
            t.setText("AUTHOR :"+"Anonymous");
        ImageView img=(ImageView)listItemView.findViewById(R.id.image);
        if(!(local_book.getURL().isEmpty()))
            Picasso.get().load(local_book.getURL()).into(img);
        else
            img.setImageResource(R.drawable.ic_launcher_foreground);
        return listItemView;
    }

}

