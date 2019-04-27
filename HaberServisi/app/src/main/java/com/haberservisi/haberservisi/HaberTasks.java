package com.haberservisi.haberservisi;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HaberTasks {
    public static class IncreaseLike extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... title) {
            Log.w("DENEME", "increaseLike " + title[0] + " likelanıyor.. ");
            return NewsWdslService.increaseLike(title[0]);
        }
    }

    public static class IncreaseDislike extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... title) {
            Log.w("DENEME", "increaseDislike " + title[0] + " dislikelanıyor.. ");
            return NewsWdslService.increaseDislike(title[0]);
        }
    }

    public static class getNews extends AsyncTask<String, Void, News> {
        @Override
        protected News doInBackground(String... title) {
            Log.e("DENEME", "getNews " + title[0] + " viewlanıyor");
            return NewsWdslService.getNews(title[0]);
        }
        @Override
        protected void onPostExecute(News result) {
            Log.e("DENEME", "news: on post execute çalıştı");
           // ItemListActivity.onPostGetNews(result);
        }
    }

    public static class getTitles extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... title_info) {
            Log.e("DENEME", "get titles için background çalıştı: " + title_info[0]);
            return NewsWdslService.getTitles(title_info[0], title_info[1], title_info[2]);
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e("DENEME", "on post execute get title için çalıştı");
            for(String res : result){
                Log.e("DENEME", "rgetTypes: " + res);
            }
            ItemListActivity.onPostGetTitles(result);
        }
    }

    public static class getTypes extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... nothing) {
            return NewsWdslService.getTypes();
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e("DENEME", "on post execute çalıştı");
            for(String res : result){
                Log.e("DENEME", "rgetTypes: " + res);
            }
            ItemListActivity.onPostGetTypes(result);
        }
    }



}
