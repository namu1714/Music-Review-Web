package review.service;

import java.util.Map;

public class ModifyRequest {
	private String userId;
	private int commentNumber;
	private String content;
	
	public ModifyRequest(String userId, int commentNumber, String content) {
		this.userId = userId;
		this.commentNumber = commentNumber;
		this.content = content;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public int getCommentNumber() {
		return commentNumber;
	}
	
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(content==null || content.trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}
}
