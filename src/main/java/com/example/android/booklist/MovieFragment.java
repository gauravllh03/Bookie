package com.example.android.booklist;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {


    public MovieFragment() {
        // Required empty public constructor
    }

    String s="";
    ListView movieList;
    private MovieAdapter adapter;
    ImageView nodata;
    ImageView find;
    View loadingIndicator;
    private static String imagebase="https://image.tmdb.org/t/p/w500";
    private static String GOOGLE_REQUEST_URL="https://api.themoviedb.org/3/search/movie?api_key=23278fc5b410505f522dd4530c47ef92&query=";
    public static final String LOG_TAG = MainActivity.class.getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_activity, container, false);
        setHasOptionsMenu(true);
        loadingIndicator = rootView.findViewById(R.id.loading);
        movieList= (ListView) rootView.findViewById(R.id.listView);
        nodata=(ImageView)rootView.findViewById(R.id.text_no_data_found);
        find=(ImageView)rootView.findViewById(R.id.front);
        ImageButton img=(ImageButton)rootView.findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et=(EditText)rootView.findViewById(R.id.editText);
                s=et.getText().toString();
                GOOGLE_REQUEST_URL="https://api.themoviedb.org/3/search/movie?api_key=23278fc5b410505f522dd4530c47ef92&query=";
                GOOGLE_REQUEST_URL+=s.trim().replaceAll("\\s+","+");
                loadingIndicator.setVisibility(View.VISIBLE);
                find.setVisibility(View.GONE);
                Log.i(LOG_TAG,"Got the string"+s);
                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    new BookAsyncTask().execute(GOOGLE_REQUEST_URL);
                    nodata.setVisibility(View.GONE);
                }
                else
                {

                    loadingIndicator.setVisibility(View.GONE);
                    nodata.setImageResource(R.drawable.nointernet);
                    nodata.setVisibility(View.VISIBLE);
                    find.setVisibility(View.GONE);
                }

            }
        });
        /*books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));
        books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));
        books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));
        books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));
        books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));
        books.add(new Book("Gaurav","Damani Steels","120","www.india.com"));*/

        adapter=new MovieAdapter(getContext(),new ArrayList<Movie>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        movieList.setAdapter(adapter);
        /*bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book local_book=adapter.getItem(position);
                if(local_book.getMdown().isEmpty() || local_book.getMdown()==null) {
                    Uri BookUri = Uri.parse(local_book.getMlink());

                    // Create a new intent to view the earthquake URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, BookUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }
                else
                {
                    Uri BookU = Uri.parse(local_book.getMdown());

                    // Create a new intent to view the earthquake URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, BookU);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                    /*Intent myIntent = new Intent(MainActivity.this,webview.class);
                    myIntent.putExtra("down",local_book.getMdown()); //Optional parameters
                    startActivity(myIntent);*/
              /*  }
            }
        });*/
        return rootView;
    }

    private class BookAsyncTask extends AsyncTask<String,Void,List<Movie>>
    {

        @Override
        protected List<Movie> doInBackground(String... urls) {
            Log.e(LOG_TAG, "Welcome"+urls[0]);
            if(urls.length>0 && urls[0]!=null) {
                Log.e(LOG_TAG, "Wecome to Bckground Method "+urls[0]);
                List<Movie> e = MovieUtils.fetchBookData(urls[0]);
                return e;
            }
            else
                return null;
        }
        @Override
        protected void onPostExecute(List<Movie> data) {
            adapter.clear();
            loadingIndicator.setVisibility(View.GONE);
            find.setVisibility(View.GONE);
            nodata.setVisibility(View.INVISIBLE);
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
                nodata.setVisibility(View.GONE);
            }
            else
            {
                nodata.setImageResource(R.drawable.nobook);
                nodata.setVisibility(View.VISIBLE);
            }
        }
    }
}


