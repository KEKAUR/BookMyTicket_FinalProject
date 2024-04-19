package com.capgemini.movieticketbooking.service.impl;

import com.capgemini.movieticketbooking.dto.MovieShowDTO;
import com.capgemini.movieticketbooking.dto.SeatDTO;
import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.exception.ShowNotFoundException;
import com.capgemini.movieticketbooking.exception.TheatreNotFoundException;
import com.capgemini.movieticketbooking.model.MovieShow;
import com.capgemini.movieticketbooking.model.Seats;
import com.capgemini.movieticketbooking.model.Theatre;
import com.capgemini.movieticketbooking.model.Ticket;
import com.capgemini.movieticketbooking.repository.MovieShowRepository;
import com.capgemini.movieticketbooking.repository.TicketRepository;
import com.capgemini.movieticketbooking.service.MovieService;
import com.capgemini.movieticketbooking.service.ShowService;
import com.capgemini.movieticketbooking.service.TheatreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
 
@Service
public class ShowServiceImpl implements ShowService{
 
    @Autowired
    private MovieShowRepository showRepository;
    
    @Autowired
    private TheatreService theatreService;
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private MovieService movieService;
 
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
            updatedShow.setShowId(showId); 
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
        return showRepository.findByMovieMovieName(movieName);
    }
 
    public List<MovieShow> getShowsByMovieNameAndCity(String movieName, String city) {
        return showRepository.findByMovieMovieNameAndTheatreAddressCity(movieName, city);
    }
 
    public List<MovieShow> getShowsByTheatreName(String theatreName) {
        return showRepository.findByTheatreTheatreName(theatreName);
    }

    public List<MovieShow> getShowsByMovieNameAndState(String movieName, String state) {
        return showRepository.findByMovieMovieNameAndTheatreAddressState(movieName, state);
    }

	@Override
	public List<MovieShow> getShowsByTheatreId(int theatreId) {
		  return showRepository.findByTheatreTheatreId(theatreId);
	}
	
	@Override
	public List<MovieShow> getShowsByMovieIdAndCity(int movieId, String city) throws ShowNotFoundException {
		 return showRepository.findByMovieMovieIdAndTheatreAddressCity(movieId, city);
	}

	@Override
	public List<Seats> getAvailableSeats(int showId) throws ShowNotFoundException, TheatreNotFoundException {
		
		MovieShow show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found with ID: " + showId));
		Theatre theatre = show.getTheatre();
		return theatre.getSeats();
	}

	@Override
	public MovieShow addShow(MovieShowDTO showDto) throws MovieNotFoundException, TheatreNotFoundException {
		MovieShow movieShow = new MovieShow() ;
		movieShow.setMovie(movieService.getMovieById(showDto.getMovieId()));
		movieShow.setShowDateTime(showDto.getShowDateTime());
		movieShow.setTheatre(theatreService.getTheatreById(showDto.getTheatreId()));
		movieShow.setTickets(null);
		return showRepository.save(movieShow);
	}
	
	public List<SeatDTO> getSeatDetails(int showId) throws ShowNotFoundException, TheatreNotFoundException {
		MovieShow show = showRepository.findById(showId).orElseThrow(() -> new ShowNotFoundException("Show not found with ID: " + showId));
		List<Seats> allSeats = show.getTheatre().getSeats();
		List<SeatDTO> seatsDetails = allSeats.stream().map(x -> new SeatDTO(false,x.getSeatNumber(),-1)).collect(Collectors.toList());
		
		Set<String> seatsToUpdate = ticketRepository.findByMovieShowShowId(showId)
		        .stream()
		        .map(Ticket::getSeats)
		        .flatMap(Set::stream)
		        .map(Seats::getSeatNumber)
		        .collect(Collectors.toSet());
		System.out.println(seatsToUpdate.toString()) ;
		seatsDetails.forEach(seat -> {
			System.out.println("Hi") ;
		    if (seatsToUpdate.contains(seat.getSeatNumber())) {
		    	System.out.println(seat.getSeatNumber()) ;
		        seat.setReserved(true);
		    }
		});
	    return seatsDetails ;
	}
	
}
