package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;
import java.util.Map;

public interface MovieService {

  /**
   * Retrieves a mapping of MovieEntity objects to the number of rental days based on the provided
   * rental requests.
   *
   * @param rentalRequests a list of {@link RentMovieRq} objects that specify the movie IDs and the
   *                       number of rental days for each movie.
   * @return a map where the keys are {@link MovieEntity} objects and the values are the number of
   * rental days for each movie.
   */
  Map<MovieEntity, Integer> getMoviesToRentalDays(List<RentMovieRq> rentalRequests);
}
