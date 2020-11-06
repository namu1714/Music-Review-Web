package review.service;

import java.util.Map;

import review.model.Writer;

public class WriteCommentRequest {
	private Writer writer;
	private Integer albumNo;
	private String content;
	
	public WriteCommentRequest(Writer writer, Integer albumNo, String content){
		this.writer = writer;
		this.albumNo = albumNo;
		this.content = content;
	}
	
	public Writer getWriter() {
		return writer;
	}
	
	public int getAlbumNo() {
		return albumNo;
	}
	
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(content == null || content.trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}
}
