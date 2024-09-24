package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;

public interface MovieRentalPriceCalculationStrategy {

  /**
   * Calculates the rental price for a given movie.
   *
   * @param movie      a MovieEntity object representing the movie for which the rental price needs
   *                   to be calculated
   * @param rentalDays
   * @return a BigDecimal representing the rental price of the specified movie
   */
  BigDecimal calculate(MovieEntity movie, Integer rentalDays);

  String getStrategyName();
}
