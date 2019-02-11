package com.example.android.booklist;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class MovieUtils {
    private static final String idbase="http://api.themoviedb.org/3/movie/";
    private static final String api="/videos?api_key=23278fc5b410505f522dd4530c47ef92";

    public static final String LOG_TAG = MainActivity.class.getName();
    private MovieUtils() {
    }
    public static List<Movie> fetchBookData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Movie> movies = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return movies;
    }
    private static List<Movie> extractFeatureFromJson(String bookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Movie> movies = new ArrayList<>();
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(bookJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray movieArray = baseJsonResponse.getJSONArray("results");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject obj=movieArray.getJSONObject(i);
                String rating="",title="",img="";
                title=obj.getString("title");
                img=obj.getString("poster_path");
                rating=obj.getString("vote_average");
                String summary="",date="";
                summary=obj.getString("overview");
                date=obj.getString("release_date");
                String poster="";
                poster=obj.getString("backdrop_path");
                String id=" ";
                id=obj.getString("id");
                Log.e(LOG_TAG,id);
                String u=idbase+id+api;
                Log.e(LOG_TAG,u);
                URL trailer = createUrl(u);
                String jResponse = null;
                try {
                    jResponse = makeHttpRequest(trailer);
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem making the HTTP request.", e);
                }
                JSONObject root=new JSONObject(jResponse);
                //JSONArray key=root.getJSONArray("results");
                //Log.e(LOG_TAG,"Length is "+key.length());
                String k = "";
                if(root.getJSONArray("results").length()!=0) {
                    JSONObject o = root.getJSONArray("results").getJSONObject(0);

                    k = o.getString("key");
                }
                else
                {
                    k="";
                }
                Log.e("Id api ",u);
                Movie movie = new Movie(title,rating,img,k,summary,date,poster);
                // Add the new {@link Earthquake} to the list of earthquakes.
                movies.add(movie);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return movies;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
