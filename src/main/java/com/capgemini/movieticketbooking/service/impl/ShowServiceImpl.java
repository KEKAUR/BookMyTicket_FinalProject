package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;
import com.capgemini.movieticketbooking.service.ShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class ShowServiceImpl implements ShowService{
 
    @Autowired
    private MovieShowRepository showRepository;
 
    public void addShow(MovieShow show) {
        showRepository.save(show);
    }
 
    public List<MovieShow> getAllShows() {
        return showRepository.findAll();
    }
 
    public MovieShow getShowById(int showId) throws ShowNotFoundException {
        Optional<MovieShow> optionalShow = showRepository.findById(showId);
        return optionalShow.orElseThrow(() -> new ShowNotFoundException("Show not found with ID: " + showId));
    }
 
    public void updateShowById(int showId, MovieShow updatedShow) throws ShowNotFoundException {
        Optional<MovieShow> optionalShow = showRepository.findById(showId);
        if (optionalShow.isPresent()) {
            updatedShow.setShowId(showId); // Ensure ID is set in the updated show object
            showRepository.save(updatedShow);
        } else {
            throw new ShowNotFoundException("Show not found with ID: " + showId);
        }
    }
 
    public void deleteShowById(int showId) throws ShowNotFoundException {
        Optional<MovieShow> optionalShow = showRepository.findById(showId);
        if (optionalShow.isPresent()) {
            showRepository.deleteById(showId);
        } else {
            throw new ShowNotFoundException("Show not found with ID: " + showId);
        }
    }
 
    public List<MovieShow> getShowsByMovieName(String movieName) {
        return showRepository.findByMovieName(movieName);
    }
 
    public List<MovieShow> getShowsByMovieNameAndCity(String movieName, String city) {
        return showRepository.findByMovieNameAndCity(movieName, city);
    }
 
    public List<MovieShow> getShowsByTheatreName(String theatreName) {
        return showRepository.findByTheatreName(theatreName);
    }

	@Override
	public MovieShow getSeatId(int seatId) throws ShowNotFoundException {
		MovieShow movieShow=showRepository.findBySeatId(seatId);
		if(movieShow==null) {
			throw new ShowNotFoundException("there is no movie show with id");
		}	
		return movieShow;
	}
 
//    public List<MovieShow> getShowsByMovieNameAndState(String movieName, String state) {
//        return showRepository.findByMovieNameAndState(movieName, state);
//    }

	
 
}
