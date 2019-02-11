package com.example.android.booklist;

import android.widget.ScrollView;

public class Book {
    private String mtitle;
    private String mlink;
    private String murl;
    private String mauthor;
    private String mdown;
    private String misbn;
    private Boolean mtext;
    private Boolean mimg;
    Book(String title,String url,String link,String author,String down,String isbn,Boolean text,Boolean img)
    {
        mtitle=title;
        murl=url;
        mlink=link;
        mauthor=author;
        mdown=down;
        misbn=isbn;
        mtext=text;
        mimg=img;
    }

    public Boolean getMtext() {
        return mtext;
    }

    public Boolean getMimg() {
        return mimg;
    }

    public String getMisbn() {
        return misbn;
    }

    public String getMdown() {
        return mdown;
    }

    public String getTitle()
    {
        return mtitle;
    }
    public String getURL()
    {
        return murl;
    }
    public String getMlink()
    {
        return mlink;
    }

    public String getMauthor() {
        return mauthor;
    }
}
