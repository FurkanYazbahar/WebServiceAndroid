package com.haberservisi.haberservisi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Base64;

public class DetailPage extends AppCompatActivity {
    ImageView imageview;
    Button like, dislike;
    TextView newsText, newsTitle;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        setGUI();

        News news = HaberThread.getNewsStart(getIntent().getStringExtra("title"));
        NewsWdslService.increaseView(news.getTitle());
        like.setText(String.valueOf(news.getLike()));
        dislike.setText(String.valueOf(news.getDislike()));
        newsTitle.setText(news.getTitle());

        title = news.getTitle();
        newsText.setText(news.getDate());
        newsText.append("\n");
        newsText.append("Görüntüleme Sayısı: ");
        newsText.append(String.valueOf(news.getView()));
        newsText.append("\n");
        newsText.append(news.getContent());

        try{
            Log.e("DENEME", "Bitmap: " + news.getPicture().length);
            Bitmap bm = BitmapFactory.decodeByteArray(news.getPicture(), 0, news.getPicture().length);
            imageview.setImageBitmap(bm);

            //String decoded = new String(Base64.getDecoder().decode(news.getPicture()));
/*
            byte[] arr  = Base64.getDecoder().decode(news.getPicture());
//            Bitmap bm = BitmapFactory.decodeByteArray(news.getPicture(), 0, news.getPicture().length);
*/
        } catch(Exception e){
            Log.e("DENEME", "Bitmap hatası! " + e);
        }
/*
        Bitmap bm = BitmapFactory.decodeByteArray(news.getPicture(), 0, news.getPicture().length);
        Log.e("DENEME", "dıbıtıs");
        /*
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
/*
        imageview.setMinimumHeight(dm.heightPixels);
        imageview.setMinimumWidth(dm.widthPixels);
        imageview.setImageBitmap(bm);
//*/

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HaberThread.increaseLike(title);
                int val = Integer.valueOf(like.getText().toString()) + 1;
                like.setText(String.valueOf(val));
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HaberThread.increaseDislike(title);
                int val = Integer.valueOf(dislike.getText().toString()) + 1;
                dislike.setText(String.valueOf(val));
            }
        });
    }

    private void setGUI(){
        imageview = findViewById(R.id.newsImage);
        like      = findViewById(R.id.like);
        dislike   = findViewById(R.id.dislike);
        newsText  = findViewById(R.id.newsText);
        newsText.setMovementMethod(new ScrollingMovementMethod());
        newsTitle = findViewById(R.id.newsTitle);
    }
}
