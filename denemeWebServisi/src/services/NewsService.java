package services;

import java.util.LinkedList;
import java.util.Vector;

import dao.DaoNews;
import model.News;

public class NewsService {
	public boolean increaseLike(String title) {
		return DaoNews.increase_value(title, "like");
	}
	
	public boolean increaseDislike(String title) {
		return DaoNews.increase_value(title, "dislike");
	}
	
	private boolean increaseView(String title) {
		return DaoNews.increase_value(title, "view");
	}
	
	public News getNews(String title) {
		increaseView(title);
		return DaoNews.getNews(title);
	}
	
	public Vector<String> getTitles(String types, int start_index, int finish_index) {
		LinkedList<String> news_types = new LinkedList<String>();
		String[] type_array = types.split(",");
		
		for(int i = 0; i < type_array.length; i++ ) {
			news_types.add(type_array[i]);
		}
		
		Vector<String> results = DaoNews.getTitles(start_index, finish_index, news_types);

		return results;
	}
	
	public static Vector<String> getTypes(){
		LinkedList<String> results = DaoNews.getTypes();
		Vector<String> types   = new Vector<String>();
		
		for(String element : results) {
			String[] type_array = element.split(",");
			for(int i = 0; i < type_array.length; i++ ) {
				if(!types.contains(type_array[i])) {
		            types.add(type_array[i]);
		        }
			}
		}    
	    
		return types;
	}
	
	public static boolean delete(String title) {
		return DaoNews.delete(title);
	}
	
	public static boolean insertNews(String title, String content, String type, String urlLink) {
		return DaoNews.insertNews(title, content, type, urlLink);
	}
	
	
}
