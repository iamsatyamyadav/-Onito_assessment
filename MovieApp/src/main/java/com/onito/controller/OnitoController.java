package com.onito.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onito.dto.MovieDTO;
import com.onito.dto.MovieDTO1;
import com.onito.dto.MovieDTO2;
import com.onito.model.Movie;
import com.onito.service.CSVDataLoader;
import com.onito.service.OnitoService;
import com.opencsv.exceptions.CsvException;

@RestController
@RequestMapping("/api/v1")
public class OnitoController {
	
	@Autowired
	private CSVDataLoader csv;
	
	@Autowired
	private OnitoService os;
	
	
	@GetMapping("/movie/populate/data")
	public ResponseEntity<String> hello() throws IOException, CsvException{
		String path="src/main/resources/static/movies.csv";
		csv.loadMovieData(path);

		return new ResponseEntity<String>("movie data populated from csv file",HttpStatus.OK);
	}
	
	
	@GetMapping("/rating/populate/data")
	public ResponseEntity<String> hello1() throws IOException, CsvException{
		String path="src/main/resources/static/ratings.csv";
		csv.loadRatingData(path);

		return new ResponseEntity<String>("rating data populated from csv file",HttpStatus.OK);
	}
	
	
	@GetMapping("/longest-duration-movies")
    public ResponseEntity<List<MovieDTO>> getLongestDurationMovies() {
        List<MovieDTO>res=os.getLongestDurationMovies();
        return new ResponseEntity<List<MovieDTO>>(res,HttpStatus.OK);
    }
	
	
	@PostMapping("/new-movie")
    public ResponseEntity<String> createNewMovie(@RequestBody Movie movie) {
        return new ResponseEntity<String>(os.createNewMovie(movie),HttpStatus.CREATED);
    }
	
	
	@GetMapping("/top-rated-movies")
	public ResponseEntity<List<MovieDTO1>> getTopRatedMovies() {
	    List<MovieDTO1> res=os.getTopRatedMovies();
	    return new ResponseEntity<List<MovieDTO1>>(res,HttpStatus.OK);
	}
	
	
	@GetMapping("/genre-movies-with-subtotals")
    public ResponseEntity<List<MovieDTO2>> getGenreMoviesWithSubtotals() {
		List<MovieDTO2> res=os.getGenreMoviesWithSubtotals();
		return new ResponseEntity<List<MovieDTO2>>(res,HttpStatus.OK);
    }
	
	
	@PostMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRuntimeMinutes() {
        return new ResponseEntity<String>(os.updateRuntimeMinutes(),HttpStatus.OK);
    }


}
