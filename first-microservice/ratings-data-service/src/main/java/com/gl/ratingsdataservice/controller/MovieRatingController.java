package com.gl.ratingsdataservice.controller;

import com.gl.ratingsdataservice.model.MovieRating;
import com.gl.ratingsdataservice.model.UserRating;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ratingData")
public class MovieRatingController {

    @RequestMapping("/{movieId}")
    public MovieRating getRating(@PathVariable("movieId") String movieId) {
        return new MovieRating(movieId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String movieId) {

        List<MovieRating> movieRatings = Arrays.asList(
                new MovieRating("1234", 4),
                new MovieRating("5678", 3)
        );

        UserRating userRating = new UserRating();
        userRating.setMovieRatings(movieRatings);

        return userRating;
    }
}
