package com.srishti.quoteoftheday;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.srishti.quoteoftheday.R.id.image_url;

/**
 * Created by srishtic on 2/12/18.
 */

public class DetailActivity extends AppCompatActivity {
    private OkHttpClient client;
    private String url="http://quotes.rest/qod.json?category=";
    private TextView tvQuote;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        tvQuote=(TextView)findViewById(R.id.quote);
        imageView=(ImageView) findViewById(R.id.image_url);
        String type=getIntent().getExtras().getString("type");


        final ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view);
        container.startShimmerAnimation();


        Request request = new Request.Builder()
                .url(url+type)
                .build();
        client = new OkHttpClient();





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
                            container.stopShimmerAnimation();

                            JSONObject json = new JSONObject(myResponse);
                            JSONObject content=json.getJSONObject("contents");
                            JSONArray quotesArray=content.getJSONArray("quotes");
                            JSONObject quote=(JSONObject)quotesArray.get(0);
                            String quotefotheday=quote.getString("quote");
                            String background=quote.getString("background");
                            Picasso.with(DetailActivity.this).load(background).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);

                            tvQuote.setText(quotefotheday);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}
