package com.rythmos.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rythmos.movieinfoservice.modle.Movie;

@RestController
@RequestMapping("/movies")
public class MovieInfoResources {
	@RequestMapping("/{movieID}")
	public Movie getMovieInfo(@PathVariable("movieID") String movieID) {
		return new Movie(movieID,"RRR");
		
	}
	

}
