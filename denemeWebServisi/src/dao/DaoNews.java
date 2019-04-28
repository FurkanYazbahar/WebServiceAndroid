package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import model.News;
import utilities.DBOpener;

public class DaoNews {

	public static PreparedStatement ps  = null;
	public static Connection        con = null;
	public static ResultSet         rs  = null;
	
	public static Connection connectionOpen() {
		return DBOpener.open();
	}
	
	public static void connectionClose(ResultSet rs, PreparedStatement ps, Connection con) {
	    try { rs.close();  } catch (Exception e) { /* ignored */ }
	    try { ps.close();  } catch (Exception e) { /* ignored */ }
	    try { con.close(); } catch (Exception e) { /* ignored */ }
	}
	
	public static boolean execute(String query, String type) {
		boolean statu = false;
		try {
			con = connectionOpen();
			ps  = (PreparedStatement) con.prepareStatement(query);
			ps.executeUpdate();
			statu = true;
		} catch (Exception e) {
			System.out.println("DB: Error in " + type + " !");			
		} finally {
			connectionClose(rs, ps, con);
		}
		return statu;
	}
	
	public static boolean delete(String title) {
		String where = "title = '" + title;
		String query = "DELETE FROM news WHERE " + where + "';";
		return execute(query, "Deleting");
	};
	
	/*
	 * types = like, dislike, view
	 */
	public static boolean increase_value(String title, String type) {
		
		String query = "UPDATE news SET news." + type +
				" = news." + type + " + 1 WHERE title = \'" + title + "\';"; 
		return execute(query, type);
	};
	
	public static Vector<String> getTitles(int startIndex, int finishIndex, List<String> types){
		Vector<String> titles = new Vector<String>();
		
		String typeString = "";
		for (int i = 0; i < types.size(); i++) {
			typeString += " news.type LIKE '" + types.get(i) + "' ";
			typeString += (i + 1 == types.size())? " ": " OR ";
		}
		
		String query = "SELECT DISTINCT news.title " +
				" FROM news " +
				" WHERE " + typeString +
				" ORDER BY date, title "+
				" LIMIT " + java.lang.String.valueOf(startIndex) + " , " + java.lang.String.valueOf(finishIndex);
		
		try {
			con = connectionOpen();	
			ps = (PreparedStatement	) con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				titles.add(rs.getString("title"));
			}
		} catch (Exception e){
			System.out.println("db: listing error!" + e);
		} finally {
			connectionClose(rs, ps, con);
		}

		return titles;
	}	
	
	public static LinkedList<String> getTypes(){
		LinkedList<String> titles = new LinkedList<String>();
				
		String query = "SELECT DISTINCT(news.type) FROM news_db.news ORDER BY news.type;";
		
		try {
			con = connectionOpen();	
			ps = (PreparedStatement	) con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				titles.add(rs.getString("type"));
			}
		} catch (Exception e){
			System.out.println("db: listing error!" + e);
		} finally {
			connectionClose(rs, ps, con);
		}

		return titles;
	}
	
	public static News getNews(String title) {
		News news = new News();
		String query = "SELECT * FROM news WHERE title = '" + title + "';";
		
		try {
			con = connectionOpen();	
			ps = (PreparedStatement	) con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setType(rs.getString("type"));
				news.setDate(rs.getString("date"));
				news.setLike(rs.getInt("like"));
				news.setDislike(rs.getInt("dislike"));
				news.setView(rs.getInt("view"));
				// alma yolunu boþ zamanýnda bul! Blob alýnacak
				// news.setPicture(rs.getInt("picture"));
				news.setPictureLink(rs.getString("picture_link"));
			}
		} catch (Exception e){
			System.out.println("db: listing error!" + e);
		} finally {
			connectionClose(rs, ps, con);
		}	
		
		return news;
	}
	
	// add create new function
	//INSERT INTO `news`(title, content, type ) VALUES ('BACKFLÝP', 'BACKFLÝPB', 'spor');
	public static boolean insertNews(String title, String content, String type, String pictureLink) {
		String query = "INSERT INTO `news`(title, content, type, picture_link ) VALUES (" +
									"'" + title         + "'," +
									"'" + content       + "'," +
									"'" + type          + "'," +
									"'" + pictureLink   + "');";
		return execute(query, "inserting News");
	};	
	public static boolean insertNewsWithPicture(String title, String content, String type, String pictureLink, String picture) {
		String query = "INSERT INTO `news`(title, content, type, picture_link, picture ) VALUES (" +
									"'" + title        + "'," +
									"'" + content      + "'," +
									"'" + type         + "'," +
									"'" + pictureLink  + "'," +
									"'" + picture      + "');";
		return execute(query, "inserting News");
	};	
	
}