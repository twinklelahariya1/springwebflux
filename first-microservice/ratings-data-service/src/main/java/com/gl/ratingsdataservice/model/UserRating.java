package com.gl.ratingsdataservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserRating {

    private List<MovieRating> movieRatings;

}
