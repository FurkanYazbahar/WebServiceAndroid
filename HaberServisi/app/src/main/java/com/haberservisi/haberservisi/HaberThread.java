package com.haberservisi.haberservisi;

import android.util.Log;

import java.util.ArrayList;

public class HaberThread {
    public HaberThread(){

    }

    public void getTypesStart(){
        new Thread(new GetTypes()).start();
    }
    public void getNewsStart(String title){
        new Thread(new GetNews(title)).start();
    }
    public void getTitlesStart(String title, String start_index,String finish_index){
        new Thread(new GetTitles(title, start_index, finish_index)).start();
    }

    class GetTitles implements Runnable {
        String title, start_index, finish_index;
        public GetTitles(String title, String start_index,String finish_index) {
            this.title = title;
            this.start_index  = start_index;
            this.finish_index = finish_index;
        }

        @Override
        public void run() {
            ArrayList<String> arr = NewsWdslService.getTitles(title, start_index, finish_index);
            for (int i = 0; i < arr.size(); i++) {
                Log.e("DENEME", "res: " + arr.get(i));
                //zgetNewsStart(arr.get(i));
            }
        }
    }

    class GetTypes implements Runnable {
        @Override
        public void run() {
            ArrayList<String> ty = NewsWdslService.getTypes();
            for (int i = 0; i < ty.size(); i++) {
                Log.e("DENEME", "res: " + ty.get(i));
            }
            ItemListActivity.onPostGetTypes(ty);
        }
    }

    class GetNews implements Runnable {
        String title;
        public GetNews(String title) {
            this.title = title;
        }

        @Override
        public void run() {
            News n = NewsWdslService.getNews(title);
            ItemListActivity.onPostGetNews(n);
            Log.e("DENEME", "res: " + n.getContent());
        }
    }

}
