package com.casumo.videorentalservice.business.rental.validator;

import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;

public interface CustomerBalanceValidator {

  /**
   * Validates the customer's balance to ensure it is sufficient for renting the provided movies.
   *
   * @param customer the customer entity whose balance will be validated.
   * @param movie    movie that the customer is attempting to rent.
   */
  void validate(CustomerEntity customer, MovieEntity movie);
}
