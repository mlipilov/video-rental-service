package com.casumo.videorentalservice.business.rental.validator;

import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;

public interface RentMovieRqValidator {

  /**
   * Validates a list of rental requests to ensure they meet the necessary criteria for renting
   * movies.
   *
   * @param rentalRequests A list of RentMovieRq objects, where each object contains the movie ID
   *                       and the number of rental days requested for each movie.
   */
  void validate(List<RentMovieRq> rentalRequests);
}
