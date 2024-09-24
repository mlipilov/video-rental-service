package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.util.Map;

public interface RentalCheckoutService {

  /**
   * Processes the payment and rental of a list of movies for a given customer.
   *
   * @param moviesMap A map containing MovieEntity objects as keys and the number of rental days as
   *                  values.
   * @param customer  The CustomerEntity object representing the customer renting the movies.
   */
  void payAndRent(Map<MovieEntity, Integer> moviesMap, CustomerEntity customer);
}
