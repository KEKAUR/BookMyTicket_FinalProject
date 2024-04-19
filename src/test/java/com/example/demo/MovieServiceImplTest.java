package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.movieticketbooking.exception.MovieNotFoundException;
import com.capgemini.movieticketbooking.model.Movie;
import com.capgemini.movieticketbooking.repository.MovieRepository;
import com.capgemini.movieticketbooking.service.impl.MovieServiceImpl;

public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMovie() {
        Movie newMovie = new Movie();
        movieService.addMovie(newMovie);

        verify(movieRepository, times(1)).save(newMovie);
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(new Movie());
        mockMovies.add(new Movie());

        when(movieRepository.findAll()).thenReturn(mockMovies);

        List<Movie> movies = movieService.getAllMovies();

        assertEquals(2, movies.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testGetMovieById_ExistingId() throws MovieNotFoundException {
        int existingId = 1;
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(existingId);

        when(movieRepository.findById(existingId)).thenReturn(Optional.of(mockMovie));

        Movie movie = movieService.getMovieById(existingId);

        assertNotNull(movie);
        assertEquals(existingId, movie.getMovieId());
        verify(movieRepository, times(1)).findById(existingId);
    }

    @Test
    public void testGetMovieById_NonExistingId() {
        int nonExistingId = 100;

        when(movieRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.getMovieById(nonExistingId);
        });
        verify(movieRepository, times(1)).findById(nonExistingId);
    }

    @Test
    public void testGetMovieByName_ExistingName() throws MovieNotFoundException {
        String existingName = "Test Movie";
        Movie mockMovie = new Movie();
        mockMovie.setMovieName(existingName);

        when(movieRepository.findByNameIgnoreCase(existingName)).thenReturn(Optional.of(mockMovie));

        Movie movie = movieService.getMovieByName(existingName);

        assertNotNull(movie);
        assertEquals(existingName, movie.getMovieName());
        verify(movieRepository, times(1)).findByNameIgnoreCase(existingName);
    }

    @Test
    public void testGetMovieByName_NonExistingName() {
        String nonExistingName = "Nonexistent Movie";

        when(movieRepository.findByNameIgnoreCase(nonExistingName)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.getMovieByName(nonExistingName);
        });
        verify(movieRepository, times(1)).findByNameIgnoreCase(nonExistingName);
    }

    @Test
    public void testDeleteMovieById_ExistingId() throws MovieNotFoundException {
        int existingId = 1;

        when(movieRepository.existsById(existingId)).thenReturn(true);

        movieService.deleteMovieById(existingId);

        verify(movieRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeleteMovieById_NonExistingId() {
        int nonExistingId = 100;

        when(movieRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.deleteMovieById(nonExistingId);
        });
        verify(movieRepository, times(0)).deleteById(any()); // No delete should occur
    }

//    @Test
//    public void testUpdateMovieById_ExistingId() throws MovieNotFoundException {
//        int existingId = 1;
//        Movie updatedMovie = new Movie();
//        updatedMovie.setMovieName("Updated Movie");
//
//        Movie mockMovie = new Movie();
//        mockMovie.setMovieId(existingId);
//
//        when(movieRepository.existsById(existingId)).thenReturn(true);
//        when(movieRepository.save(any())).thenReturn(updatedMovie);
//
//        movieService.updateMovieById(existingId, updatedMovie);
//
//        assertEquals("Updated Movie", mockMovie.getMovieName());
//        verify(movieRepository, times(1)).save(mockMovie);
//    }

    @Test
    public void testUpdateMovieById_NonExistingId() {
        int nonExistingId = 100;
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieName("Updated Movie");

        when(movieRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(MovieNotFoundException.class, () -> {
            movieService.updateMovieById(nonExistingId, updatedMovie);
        });
        verify(movieRepository, times(0)).save(any()); // No save should occur
    }
}
