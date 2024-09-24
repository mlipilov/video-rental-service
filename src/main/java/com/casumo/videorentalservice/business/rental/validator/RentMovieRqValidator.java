package com.casumo.videorentalservice.business.rental.validator;

import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;

public interface RentMovieRqValidator {

  /**
   * Validates the list of rental requests to ensure they meet the necessary criteria.
   *
   * @param rentalRequests a list of {@link RentMovieRq} objects that need to be validated. Each
   *                       {@link RentMovieRq} contains the movie ID and the number of rental days
   *                       requested.
   */
  void validate(List<RentMovieRq> rentalRequests);
}
