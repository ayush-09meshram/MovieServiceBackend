package com.stackroute.movieservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.movieservice.domain.Movie;
import com.stackroute.movieservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieservice.exceptions.MovieNotFoundException;
import com.stackroute.movieservice.repository.MovieRepository;
import com.stackroute.movieservice.service.MovieService;
import com.stackroute.movieservice.service.MovieServiceImpl2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//find out difference between @Mock and @MockBean

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private MovieRepository movieRepository;
    private Movie movie;
    @MockBean
    private MovieServiceImpl2 movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieTitle("John");
        movie.setImdbId(101);
        movie.setYearOfRelease("Jenny");
        movie.setRating(10);
        list = new ArrayList<>();
        list.add(movie);
    }

    @Test
    public void saveMovie() throws Exception {
        when(movieService.saveMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void saveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMovie() throws Exception {
        when(movieService.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAllMovies")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteMovie() throws Exception{
        movieService.deleteMovie(movie);
        when(movieService.getMovieByName(movie.getMovieTitle())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isGone())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteMovieFailure() throws Exception{
        movieService.deleteMovie(movie);
        when(movieService.getMovieByName(movie.getMovieTitle())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMovie() throws Exception {
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("John");
        updatedMovie.setImdbId(101);
        updatedMovie.setYearOfRelease("Jenny");
        updatedMovie.setRating(10);
        when(movieService.updateMovie(movie)).thenReturn(updatedMovie);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMovieFailure() throws Exception{
       /* movieService.deleteMovie(movie);
        Movie updatedMovie = new Movie();
        updatedMovie.setMovieTitle("John");
        updatedMovie.setImdbId(101);
        updatedMovie.setYearOfRelease("Jenny");
        updatedMovie.setRating(10);*/
       movieService.deleteMovie(movie);
        when(movieService.updateMovie(movie)).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void searchMovieByName() throws Exception{
        when(movieService.getMovieByName((String)any())).thenReturn(Optional.of(movie));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/John")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchMovieByNameFailure() throws Exception{
        when(movieService.getMovieByName((String)any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/John")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}
