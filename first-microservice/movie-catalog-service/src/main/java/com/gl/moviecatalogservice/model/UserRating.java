package com.gl.moviecatalogservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRating {

    private List<MovieRating> movieRatings;

}
