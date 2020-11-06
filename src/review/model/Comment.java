package review.model;

import java.time.LocalDateTime;

public class Comment {
	private Integer number;
	private Integer album;
	private Writer writer;
	private Integer likes;
	private LocalDateTime regDate;
	private LocalDateTime modifiedDate;
	private String content;
	
	public Comment(Integer number, Integer album, Writer writer, Integer likes, 
			LocalDateTime regDate, LocalDateTime modifiedDate, String content) {
		this.number = number;
		this.album = album;
		this.writer = writer;
		this.likes = likes;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.content = content;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public Integer getAlbum() {
		return album;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public Integer getLikes() {
		return likes;
	}
	
	public LocalDateTime getRegDate() {
		return regDate;
	}
	
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	
	public String getContent() {
		return content;
	}
}
