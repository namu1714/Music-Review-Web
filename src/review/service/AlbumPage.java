package review.service;

import java.util.List;

import review.model.Album;

public class AlbumPage {
	private int total; //전체 게시글 개수
	private int currentPage;
	private List<Album> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public AlbumPage(int total, int currentPage, int size, List<Album> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
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
	
	public boolean hasNoAlbums() {
		return total == 0;
	}
	
	public boolean hasAlbums() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<Album> getContent(){
		return content;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
