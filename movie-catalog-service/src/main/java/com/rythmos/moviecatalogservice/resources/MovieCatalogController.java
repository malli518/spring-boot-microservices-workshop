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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rythmos.moviecatalogservice.modle.CatalogItem;
import com.rythmos.moviecatalogservice.modle.Movie;
import com.rythmos.moviecatalogservice.modle.Rating;
import com.rythmos.moviecatalogservice.modle.UserRating;
import com.rythmos.moviecatalogservice.service.MovieInfo;
import com.rythmos.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalogsInfo(@PathVariable("userId") String userId) {
		
		/*List<Rating> ratings=Arrays.asList(
				new Rating("101",4),
				new Rating("102",5)); 
				*/
		UserRating userRating=userRatingInfo.getUserRating(userId);
		return userRating.getRatings().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
			
			/*Movie movie =webClientBuilder.build() //WebClient
			.get() // requestMethod (GET,POST,PUT,DELETE)
			.uri("http://localhost:2021/movies/"+rating.getMovieId()) // url 
			.retrieve() // fetch the response
			.bodyToMono(Movie.class) // async object (it is going to give in future wt u go want )
			.block(); // until you will get object block it 
			*/							
		//return Collections.singletonList(new CatalogItem("RRR", "Good", 5));				
	}

	/*
	 * public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String
	 * userId) { return Arrays.asList(new CatalogItem("No Movie","",0)); }
	 */
}
