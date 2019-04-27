package com.haberservisi.haberservisi.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.haberservisi.haberservisi.News;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class NewsContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<News> ITEMS = new ArrayList<News>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, News> ITEM_MAP = new HashMap<String, News>();

    private static final int COUNT = 10;

    static {
        /*
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addNews(createNewsItem(i));
        }
        */
    }

    private static void addNews(News item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getTitle(), item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

/*
    public static class News {

        private String id      = null;
        private String content = null;
        private String details = null;
        private String title        = null;
        private String type         = null;
        private String date         = null;
        private int    like         = 0;
        private int    dislike      = 0;
        private int    view         = 0;
        private byte[] picture      = null;
        private String pictureLink  = null;



        public News(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

//*/
}