package review.service;

import java.util.Map;

public class AddAlbumRequest {
	private String title;
	private String artist;
	private Integer releaseYear;
	private String coverImage;
	
	public AddAlbumRequest(String title, String artist, Integer releaseYear, String coverImage) {
		this.title = title;
		this.artist = artist;
		this.releaseYear = releaseYear;
		this.coverImage = coverImage;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public Integer getReleaseYear() {
		return releaseYear;
	}
	
	public String getCoverImage() {
		return coverImage;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		if(artist == null || artist.trim().isEmpty()) {
			errors.put("artist", Boolean.TRUE);
		}
		if(releaseYear == null) {
			errors.put("releaseYear", Boolean.TRUE);
		}
	}
}
