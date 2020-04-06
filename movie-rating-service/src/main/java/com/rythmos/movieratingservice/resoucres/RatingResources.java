package com.rythmos.movieratingservice.resoucres;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rythmos.movieratingservice.modle.Rating;
import com.rythmos.movieratingservice.modle.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingResources {
	
	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,5);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratings=Arrays.asList(
				new Rating("101",4),
				new Rating("102",5)); 
		UserRating userRating=new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}


}
