package model;

public class News {
	private int    id           = 0;
	private String title        = null;
	private String content      = null;
	private String type         = null;
	private String date         = null;
	private int    like         = 0;
	private int    dislike      = 0;
	private int    view         = 0;
	private byte[] picture      = null;
	private String pictureLink  = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public String getPictureLink() {
		return pictureLink;
	}
	public void setPictureLink(String picture_link) {
		this.pictureLink = picture_link;
	}	
}
