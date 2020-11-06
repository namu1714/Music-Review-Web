package review.model;

public class Album {
	private Integer number;
	private String title;
	private String artist;
	private Integer releaseYear;
	private String coverImage;
	
	public Album(Integer number, String title, String artist, Integer releaseYear, String coverImage) {
		this.number = number;
		this.title = title;
		this.artist = artist;
		this.releaseYear = releaseYear;
		this.coverImage = coverImage;
	}
	
	public Integer getNumber() {
		return number;
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
	
	public String getImage() {
		return coverImage;
	}
}