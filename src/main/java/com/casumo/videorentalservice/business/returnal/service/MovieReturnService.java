package com.casumo.videorentalservice.business.returnal.service;

import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import java.util.Set;

public interface MovieReturnService {

  /**
   * Processes the return of movies for a given customer.
   *
   * @param rentals         The set of {@link RentalEntity} representing the movies to be returned.
   * @param currentCustomer The {@link CustomerEntity} who is returning the movies.
   * @return A set of {@link RentalEntity} that have been returned.
   */
  Set<RentalEntity> returnMovies(Set<RentalEntity> rentals, CustomerEntity currentCustomer);
}
