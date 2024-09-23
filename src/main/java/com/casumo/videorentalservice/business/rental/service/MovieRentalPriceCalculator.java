package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;

public interface MovieRentalPriceCalculator {

  /**
   * Calculates the total rental price for a list of movies.
   *
   * @param movie a list of MovieEntity objects representing the movies to be rented
   * @return a BigDecimal representing the total rental price for the provided list of movies
   */
  BigDecimal calculate(MovieEntity movie);
}
