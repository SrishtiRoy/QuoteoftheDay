package com.srishti.quoteoftheday;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.srishti.quoteoftheday.cardview.Quotes;
import com.srishti.quoteoftheday.cardview.QuotesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by srishtic on 2/14/18.
 */

public class RandomQuoteActivity extends AppCompatActivity {
    //Created by Rajat Gupta on 18/08/16

    private RecyclerView recyclerView;
    private QuotesAdapter adapter;
    private List<Quotes> quotesList;
    private String url="http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=44";
    private ArrayList<Quotes> arrQuotes=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        quotesList = new ArrayList<>();
        adapter = new QuotesAdapter(this, quotesList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getQuotes();

        try {
            //Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Adding few albums for testing
     */
    private void getQuotes() {
        Request request = new Request.Builder()
                .url(url)
                .build();
       OkHttpClient client = new OkHttpClient();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONArray array = new JSONArray(myResponse.toString());
                            for(int i=0;i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                                String quotefotheday = obj.getString("title");
                                String content = obj.getString("content");
                                Quotes quote=new Quotes(content,quotefotheday);
                                quotesList.add(quote);
                                recyclerView.setAdapter(adapter);

                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        adapter.notifyDataSetChanged();

    }




}
