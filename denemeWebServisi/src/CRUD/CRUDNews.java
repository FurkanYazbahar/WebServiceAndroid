package CRUD;

import utilities.DBOpener;

import java.util.ArrayList;
import java.util.List;

import dao.DaoNews;
import model.News;

public class CRUDNews {
	public static void main(String[] args) {
		//DBOpener.open();
		/*
		News n = DaoNews.getNews("BACKFL�P");
		System.out.println(n.getContent());
		*/
		
		//DaoNews.increase_value("DOLAR", "like");
		
		// DaoNews.insertNews("AtKafas�", "deneme1234567890*sdfghjkl�i,asdfghjkl�i,","deneme");
		/* // add value
		DaoNews.increase_value("AMUT", "view");
		DaoNews.increase_value("AMUT", "like");
		DaoNews.increase_value("AMUT", "dislike");
		DaoNews.delete("AMUT");
		*/
		
		/*// getTitles
		List<String> types = new ArrayList<String>();
		types.add("spor");
		types.add("sanat");
		List<String> res = DaoNews.getTitles(0, 5, types);
		for(String r: res) {
			System.out.println(r);
		}
		*/
	}
	
}
