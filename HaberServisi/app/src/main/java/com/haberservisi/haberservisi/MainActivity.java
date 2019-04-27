package com.haberservisi.haberservisi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static String tag = "DENEME";
    private static News news = null;
    private static ArrayList<String> types;
    private static ArrayList<String> titles;
    private Button showButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButton = findViewById(R.id.button);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(tag, "content : " + news.getLike());
            }
        });

        try {
/*
            HaberTasks.IncreaseLike l = new HaberTasks.IncreaseLike();
            l.execute("TL");

            HaberTasks.IncreaseDislike d = new HaberTasks.IncreaseDislike();
            d.execute("TL");

            /*
            HaberTasks.getNews n = new HaberTasks.getNews();
            n.execute("TL");
            */
/*
            HaberTasks.getTypes tt = new HaberTasks.getTypes();
            tt.execute();
*/
            HaberTasks.getTitles tt = new HaberTasks.getTitles();
            tt.execute("spor", "0", "5");
            for(String s : titles) {
                Log.e(tag, "title: " + s);
            }

            //Toast.makeText(getApplicationContext(),"biddı", Toast.LENGTH_SHORT);

        } catch(Exception e){
            Log.e(tag, "error:" + e);
        }

    }


    public static void onPostIncrease(String resp)
    {
        Log.d(tag, "onPostIncrease resp: "  + resp);
    }

    public static void onPostGetNews(News response)
    {
        Log.d(tag, "onPostGet News response: "  );
        news = response;
    }

    public static void onPostGetTypes(ArrayList<String> results)
    {
        Log.d(tag, "onPostGet Types response: "  );
        types = results;
    }

    public static void onPostGetTitles(ArrayList<String> results)
    {
        Log.d(tag, "onPostGet Titles response: "  );
        titles = results;
    }
}
