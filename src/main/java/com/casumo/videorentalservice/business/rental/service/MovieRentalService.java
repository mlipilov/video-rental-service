package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;
import java.util.Set;

public interface MovieRentalService {

  /**
   * Creates rental entries based on a list of rental requests.
   *
   * @param rentalRequests a list of {@link RentMovieRq} objects that specify the movie IDs and the
   *                       number of rental days for each movie.
   * @return a set of {@link RentalEntity} objects representing the created rental records.
   */
  Set<RentalEntity> createRental(List<RentMovieRq> rentalRequests);
}
