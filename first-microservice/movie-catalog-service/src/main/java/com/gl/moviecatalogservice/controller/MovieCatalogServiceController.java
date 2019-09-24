package com.gl.moviecatalogservice.controller;

import com.gl.moviecatalogservice.model.CatalogItem;
import com.gl.moviecatalogservice.model.Movie;
import com.gl.moviecatalogservice.model.UserRating;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all rated movieId

        UserRating rating = restTemplate.getForObject("http://rating-data-service/ratingData/users/" + userId, UserRating.class);

        return rating.getMovieRatings().stream().map(movieRating -> {
            //for each id call movie info service and get all details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + movieRating.getMovieId(), Movie.class);
            //put them all together
            return new CatalogItem(movie.getMovieName(), "test", movieRating.getRating());
        }).collect(Collectors.toList());


    }
}











/* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/movie/" + movieRating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
*/