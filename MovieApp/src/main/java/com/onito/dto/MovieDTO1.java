package com.onito.dto;

public class MovieDTO1 {

    private String tconst;
    
    private String primaryTitle;
    
    private String genres;
    
    private Double averageRating;
    
    public MovieDTO1() {
		// TODO Auto-generated constructor stub
	}
    
	public MovieDTO1(String tconst, String primaryTitle, String genres, Double averageRating) {
		super();
		this.tconst = tconst;
		this.primaryTitle = primaryTitle;
		this.genres = genres;
		this.averageRating = averageRating;
	}
	
	public String getTconst() {
		return tconst;
	}
	
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	
	public String getPrimaryTitle() {
		return primaryTitle;
	}
	
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}
	
	public String getGenres() {
		return genres;
	}
	
	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}
	
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
}