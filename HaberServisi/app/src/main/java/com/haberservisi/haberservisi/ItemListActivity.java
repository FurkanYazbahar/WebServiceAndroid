package com.haberservisi.haberservisi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ItemListActivity extends AppCompatActivity {
    private static List<String>  titles    = new ArrayList<String>();
    private static List<News>    newsList  = new ArrayList<News>();
    private static List<String>  newsTypes = new ArrayList<String>();
    static Context context;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        newsTypes  = HaberThread.getTypesStart();

        String res  = newsTypes.get(0);
        for(int i = 1; i < newsTypes.size(); i++){
            res = res + ',' + newsTypes.get(i);
        }

        context = getApplicationContext();
        titles  = HaberThread.getTitlesStart(res, "0", "1000");

        for(int i = 0; i < titles.size(); i++){
            newsList.add(HaberThread.getNewsStart(titles.get(i)));
        }

        Toolbar toolbar =  findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: BURDA type ekleme eklenecek
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView, newsList);
    }

    public static void onPostGetTitles(ArrayList<String> result)
    {
        titles = result;
        Log.d("DENEME", "onPostGet Titles response: " + titles.size());
    }

    public static void onPostGetTypes(ArrayList<String> result)
    {
        newsTypes = result;
        Log.d("DENEME", "onPostGet Titles response: "  );
        //TODO: add in shared preference
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<News> _newsList) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(_newsList));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<News> mValues;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news = (News) view.getTag();

                Context ctx = view.getContext();
                Intent intent = new Intent(ctx, DetailPage.class);
                intent.putExtra("title", news.getTitle());
                ctx.startActivity(intent);
            }
        };

        SimpleItemRecyclerViewAdapter(List<News> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            //return new ViewHolder(mImage, view);
            return new ViewHolder(null, view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

            holder.mIdView.setText(String.valueOf(mValues.get(position).getDate()));
            holder.mContentView.setText(mValues.get(position).getTitle());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImage;
            final TextView  mIdView;
            final TextView  mContentView;

            ViewHolder(ImageView mImage, View view) {
                super(view);
                this.mImage       = view.findViewById(R.id.image);
                this.mIdView      = view.findViewById(R.id.id_text);
                this.mContentView = view.findViewById(R.id.content);
            }
        }
    }
}
