package com.rythmos.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.rythmos.moviecatalogservice.modle.CatalogItem;
import com.rythmos.moviecatalogservice.modle.Movie;
import com.rythmos.moviecatalogservice.modle.Rating;
import com.rythmos.moviecatalogservice.modle.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalogsInfo(@PathVariable("userId") String userId) {
		
		/*List<Rating> ratings=Arrays.asList(
				new Rating("101",4),
				new Rating("102",5)); 
				*/
		UserRating userRating=restTemplate.getForObject("http://movie-rating-service/ratings/users/"+userId, UserRating.class);
		return userRating.getRatings().stream().map(rating -> {
			Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			/*Movie movie =webClientBuilder.build() //WebClient
			.get() // requestMethod (GET,POST,PUT,DELETE)
			.uri("http://localhost:2021/movies/"+rating.getMovieId()) // url 
			.retrieve() // fetch the response
			.bodyToMono(Movie.class) // async object (it is going to give in future wt u go want )
			.block(); // until you will get object block it 
			*/
			
			return new CatalogItem(movie.getName(),"Good",rating.getRating());
		}).collect(Collectors.toList());
		
		//return Collections.singletonList(new CatalogItem("RRR", "Good", 5));
	}

}
