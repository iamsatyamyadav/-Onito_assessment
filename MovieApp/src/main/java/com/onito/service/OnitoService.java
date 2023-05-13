package com.onito.service;

import java.util.List;

import com.onito.dto.MovieDTO2;
import com.onito.dto.MovieDTO;
import com.onito.dto.MovieDTO1;
import com.onito.model.Movie;

public interface OnitoService {

    public List<MovieDTO> getLongestDurationMovies() ;
    
    public String createNewMovie(Movie movie) ;
    
	public List<MovieDTO1> getTopRatedMovies();
	
    public List<MovieDTO2> getGenreMoviesWithSubtotals() ;
    
    public String updateRuntimeMinutes();

}
