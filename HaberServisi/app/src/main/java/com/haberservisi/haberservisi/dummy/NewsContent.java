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

    public static final List<News> ITEMS = new ArrayList<News>();

    public static final Map<String, News> ITEM_MAP = new HashMap<String, News>();

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

}