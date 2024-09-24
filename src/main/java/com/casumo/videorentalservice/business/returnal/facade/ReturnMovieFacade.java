package com.casumo.videorentalservice.business.returnal.facade;

import com.casumo.videorentalservice.model.response.MovieReturnedRs;
import java.util.List;

public interface ReturnMovieFacade {

  /**
   * Processes the return of rented movies and calculates the total rental price.
   *
   * @param returnRq a list of movie IDs to be returned
   * @return a MovieRentedRs object containing information about the rentals and the total rental
   * price
   */
  MovieReturnedRs returnMovies(List<Long> returnRq);
}
