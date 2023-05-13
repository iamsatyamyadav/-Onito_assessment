package com.onito.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.model.Movie;
import com.onito.model.Rating;
import com.onito.repo.MovieRepo;
import com.onito.repo.RatingRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class CSVDataLoader {
	
	@Autowired
	private MovieRepo mr;
	
	@Autowired
	private RatingRepo rr;

    public void loadMovieData(String path) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();
            
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
//                System.out.println(Arrays.toString(row));
                Movie movie = new Movie();
                movie.setTconst(row[0]);
                movie.setTitleType(row[1]);
                movie.setPrimaryTitle(row[2]);
                movie.setRuntimeMinutes(Integer.parseInt(row[3]));
                movie.setGenres(row[4]);
                
                mr.save(movie);
            }
        }
    }

    public void loadRatingData(String path) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
//                System.out.println(Arrays.toString(row));
                Rating rating = new Rating();
                rating.setTconst(row[0]);
                rating.setAverageRating(Double.parseDouble(row[1]));
                rating.setNumVotes(Integer.parseInt(row[2]));

                rr.save(rating);
            }
        }
    }
}
