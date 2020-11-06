package review.service;

import java.util.List;

import review.model.Album;
import review.model.Comment;

public class CommentPage {
	private int total; //전체 게시글 개수
	private int currentPage;
	private Album album;
	private List<Comment> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public CommentPage(int total, int currentPage, int size, Album album, List<Comment> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.album = album;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total/size;
			if(total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage/5 * 5 + 1;
			if(modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if(endPage>totalPages) endPage = totalPages;
		}
	}
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoComments() {
		return total == 0;
	}
	
	public boolean hasComments() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public List<Comment> getContent(){
		return content;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
}
