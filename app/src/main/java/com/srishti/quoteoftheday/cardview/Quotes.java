package com.srishti.quoteoftheday.cardview;

/**
 * Created by Rajat Gupta on 18/05/16.
 */
public class Quotes {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    private String quote;
    private String title;


    public Quotes() {
    }

    public Quotes(String name, String title) {
        this.quote = name;
        this.title = title;
    }


}
