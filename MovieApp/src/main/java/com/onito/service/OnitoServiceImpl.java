package com.onito.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.dto.MovieDTO2;
import com.onito.dto.MovieDTO;
import com.onito.dto.MovieDTO1;
import com.onito.model.Movie;
import com.onito.repo.MovieRepo;

@Service
public class OnitoServiceImpl implements OnitoService{
	
	
	@Autowired
	private MovieRepo mr;

	
	@Override
	public List<MovieDTO> getLongestDurationMovies() {
		List<Movie> movies = mr.findTop10ByOrderByRuntimeMinutesDesc();
		
		List<MovieDTO> result =  new ArrayList<>();
		
		for(Movie m:movies) {
			
			MovieDTO md=new MovieDTO(m.getTconst(),m.getPrimaryTitle(),m.getRuntimeMinutes(),m.getGenres());
			
			result.add(md);
		}
		
		return result;
		
//        return movies.stream()
//                .map(movie -> new MovieDTO(movie.getTconst(), movie.getPrimaryTitle(), movie.getRuntimeMinutes(), movie.getGenres()))
//                .collect(Collectors.toList());
	}

	
	@Override
	public String createNewMovie(Movie movie) {
		mr.save(movie);
		return "success";
	}

	
	@Override
	public List<MovieDTO1> getTopRatedMovies() {
		List<Movie> movies=mr.findByRatingAverageRatingGreaterThan(6.0);
	    
	    movies.sort(new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				if(o1.getRating().getAverageRating()>o2.getRating().getAverageRating())
					return -1;
				
				if(o1.getRating().getAverageRating()<o2.getRating().getAverageRating())
					return 1;
				
				return o1.getTconst().compareTo(o2.getTconst());
			}
		});
	    
	    List<MovieDTO1> res= movies.stream()
	            .map(movie -> new MovieDTO1(movie.getTconst(), movie.getPrimaryTitle(), movie.getGenres(), movie.getRating().getAverageRating()))
	            .collect(Collectors.toList());
	    
	    return res;
	}

	
	@Override
	public List<MovieDTO2> getGenreMoviesWithSubtotals() {

		List<Object[]> data=mr.getGenreMoviesWithSubtotals();
        
        List<MovieDTO2> result = new ArrayList<>();
        
        Map<String,List<MovieDTO2>> map=new HashMap<>();
        
        for (Object[] obj : data) {
            String genre = (String) obj[0];
            String primaryTitle = (String) obj[1];
            Long numVotes = (Long) obj[2];
            
            List<MovieDTO2> temp=map.getOrDefault(genre, new ArrayList<>());
            
            temp.add(new MovieDTO2(genre, primaryTitle, numVotes));
            
            map.put(genre, temp);
        }
        
        for(Map.Entry<String, List<MovieDTO2>> m:map.entrySet()) {
        	long total=0;
        	List<MovieDTO2>temp=m.getValue();
        	result.addAll(temp);
        	
        	for(MovieDTO2 g:temp) {
        		total+=g.getNumVotes();
        	}
        	result.add(new MovieDTO2("","Total",total));
        }
        
        return result;
            
	}

	
	@Override
	public String updateRuntimeMinutes() {
		mr.updateRuntimeMinutes();
        return "Runtime minutes updated successfully.";
	}

}
