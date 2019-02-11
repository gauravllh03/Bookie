package com.example.android.booklist;

public class Movie {
    private String mtitle="";
    private String mrating="";
    private String mimg="";
    private String mk="";
    private String msum="";
    private String mdate="";
    private String mposter="";
    Movie(String title,String rating,String img,String key,String summary,String date,String poster)
    {
        mtitle=title;
        mrating=rating;
        mimg=img;
        mk=key;
        msum=summary;
        mdate=date;
        mposter=poster;
    }

    public String getMposter() {
        return mposter;
    }

    public String getMk() {
        return mk;
    }

    public String getMdate() {
        return mdate;
    }

    public String getMsum() {
        return msum;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getMrating() {
        return mrating;
    }

    public String getMimg() {
        return mimg;
    }
}
