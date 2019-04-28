package com.haberservisi.haberservisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        News n = getCliValues(getIntent());
        Log.e("DENEME","detail title: " + n.getTitle() + " detail like: " + n.getView());
    }

    private News getCliValues(Intent intent){
        News news = new News();
        news.setId(intent.getIntExtra("id",0));
        news.setTitle(intent.getStringExtra("title"));
        news.setContent(intent.getStringExtra("content"));
        news.setType(intent.getStringExtra("type"));
        news.setDate(intent.getStringExtra("date"));
        news.setLike(intent.getIntExtra("like",0));
        news.setDislike(intent.getIntExtra("dislike",0));
        news.setView(intent.getIntExtra("view",0));
        news.setPicture(intent.getByteArrayExtra("picture"));
        news.setPictureLink(intent.getStringExtra("pictureLink"));
        return news;
    }
}
