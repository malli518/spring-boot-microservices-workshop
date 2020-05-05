package com.rythmos.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rythmos.movieinfoservice.modle.Movie;
import com.rythmos.movieinfoservice.modle.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoResources {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.key}")
	private String apiKey;

	@RequestMapping("/{movieID}")
	public Movie getMovieInfo(@PathVariable("movieID") String movieID) {

		// https://api.themoviedb.org/3/movie/100?api_key=6fa9aebff2c1cdb42c9fa455e6110d75
		String url = "https://api.themoviedb.org/3/movie/" + movieID + "?api_key=" + apiKey;
		System.out.println("URL :: " + url);
		MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
		return new Movie(movieID, movieSummary.getTitle(), movieSummary.getOverview());

	}

}
