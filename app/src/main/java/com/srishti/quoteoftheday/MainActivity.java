package com.srishti.quoteoftheday;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;
    OkHttpClient client = new OkHttpClient();
    private String  url="http://quotes.rest/qod/categories.json";
    private TextView tv_type1;
    private TextView tv_type2;
    private TextView tv_type3;
    private TextView tv_type4;
    private TextView tv_type5;
    private TextView tv_type6;
    private TextView tv_type7;
    private TextView tv_type8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the next level button, which tries to show an interstitial when clicked.
        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
        tv_type1 = ((TextView) findViewById(R.id.type_1));
        tv_type2 = ((TextView) findViewById(R.id.type_2));
        tv_type3 = ((TextView) findViewById(R.id.type_3));
        tv_type4 = ((TextView) findViewById(R.id.type_4));
        tv_type5 = ((TextView) findViewById(R.id.type_5));
        tv_type6 = ((TextView) findViewById(R.id.type_6));
        tv_type7 = ((TextView) findViewById(R.id.type_7));
        tv_type8 = ((TextView) findViewById(R.id.type_8));
        tv_type1.setOnClickListener(this);
        tv_type2.setOnClickListener(this);
        tv_type3.setOnClickListener(this);
        tv_type4.setOnClickListener(this);
        tv_type5.setOnClickListener(this);
        tv_type6.setOnClickListener(this);
        tv_type7.setOnClickListener(this);
        tv_type8.setOnClickListener(this);


        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject json = new JSONObject(myResponse);
                            JSONObject content=json.getJSONObject("contents");
                            JSONObject categories=content.getJSONObject("categories");
                            String type1=categories.getString("inspire");
                            String type2=categories.getString("management");
                            String type3=categories.getString("sports");
                            String type4=categories.getString("life");
                            String type5=categories.getString("funny");
                            String type6=categories.getString("love");
                            String type7=categories.getString("art");
                            String type8=categories.getString("students");
                            tv_type1.setText(type1);
                            tv_type2.setText(type2);
                            tv_type3.setText(type3);
                            tv_type4.setText(type4);
                            tv_type5.setText(type5);
                            tv_type6.setText(type6);
                            tv_type7.setText(type7);
                            tv_type8.setText(type8);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_1:

                showDetail("inspire");
                break;
            case R.id.type_2:

                showDetail("management");
                break;
            case R.id.type_3:

                showDetail("sports");
                break;

            case R.id.type_4:

                showDetail("life");
                break;
            case R.id.type_5:

                showDetail("funny");
                break;

            case R.id.type_6:

                showDetail("love");
                break;

            case R.id.type_7:

                showDetail("art");
                break;

            case R.id.type_8:

                showDetail("students");
                break;
        }
    }


    private void showDetail(String type)
    {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }
}
