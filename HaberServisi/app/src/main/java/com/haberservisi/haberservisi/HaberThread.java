package com.haberservisi.haberservisi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HaberThread {
    public HaberThread(){
    }

    public static List<String> getTypesStart(){
        List<String> types = new ArrayList<String>();
        Thread t = new Thread(new GetTypes(types));
        try {
            t.start();
            t.join();
        } catch( Exception e){
            Log.e("DENEME", "getTypesStart join hatası: " + e);
        }
        return types;
    }
    public static News getNewsStart(String title){
        News news = new News();
        Thread t = new Thread(new GetNews(news, title));
        try {
            t.start();
            t.join();
        } catch( Exception e){
            Log.e("DENEME", "getTypesStart join hatası: " + e);
        }
        return news;
    }

    public static List<String> getTitlesStart(String title, String start_index, String finish_index){
        List<String> titles = new ArrayList<String>();
        Thread t = new Thread(new GetTitles(titles, title, start_index, finish_index));
        try {
            t.start();
            t.join();
        } catch( Exception e){
            Log.e("DENEME", "getTitlesStart join hatası: " + e);
        }
        return titles;
    }

    static class GetTitles implements Runnable {
        private List<String>titles;
        String title, start_index, finish_index;
        public GetTitles(List<String> _titles, String title, String start_index,String finish_index) {
            this.title = title;
            this.start_index  = start_index;
            this.finish_index = finish_index;
            titles = _titles;
        }

        @Override
        public void run() {
            ArrayList<String> arr = NewsWdslService.getTitles(title, start_index, finish_index);
            titles.addAll(arr);
        }
    }

    static class GetTypes implements Runnable {
        List<String> types;
        public GetTypes(List<String> _types){
            types = _types;
        }

        @Override
        public void run() {
            List<String> _types = NewsWdslService.getTypes();
            types.addAll(_types);
        }
    }

    static class GetNews implements Runnable {
        String title;
        News news;
        public GetNews(News news, String title) {
            this.title = title;
            this.news  = news;
        }

        @Override
        public void run() {
            News n = NewsWdslService.getNews(title);
            news.setId(n.getId());
            news.setTitle(n.getTitle());
            news.setContent(n.getContent());
            news.setType(n.getType());
            news.setDate(n.getDate());
            news.setLike(n.getLike());
            news.setDislike(n.getDislike());
            news.setView(n.getView());
            news.setPicture(n.getPicture());
            news.setPictureLink(n.getPictureLink());
        }
    }

}
