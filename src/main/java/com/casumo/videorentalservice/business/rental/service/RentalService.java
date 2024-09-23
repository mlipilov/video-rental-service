package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;
import java.util.Set;

public interface RentalService {

  /**
   * Creates rental records based on the provided list of rental requests.
   *
   * @param rentalRequests A list of RentMovieRq objects, each containing the movie ID and the
   *                       number of rental days requested.
   */
  Set<RentalEntity> createRental(List<RentMovieRq> rentalRequests);
}
