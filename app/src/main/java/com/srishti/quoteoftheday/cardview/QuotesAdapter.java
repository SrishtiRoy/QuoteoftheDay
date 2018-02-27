package com.srishti.quoteoftheday.cardview;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.srishti.quoteoftheday.DetailActivity;
import com.srishti.quoteoftheday.R;

import java.util.List;

/**
 * Created by Rajat Gupta on 18/05/16.
 */
public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Quotes> quotesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.quotes_title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


        }
    }


    public QuotesAdapter(Context mContext, List<Quotes> quotesList) {
        this.mContext = mContext;
        this.quotesList = quotesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quote_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Quotes quotes = quotesList.get(position);
        holder.title.setText(quotes.getQuote());
        Picasso.with(mContext).load("https://picsum.photos/200/300/?random").memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.
                thumbnail, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                holder.thumbnail.setImageResource(R.drawable.noimg);
            }

        });


    }




    @Override
    public int getItemCount() {
        return quotesList.size();
    }
}
