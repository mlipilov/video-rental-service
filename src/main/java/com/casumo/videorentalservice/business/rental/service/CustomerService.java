package com.casumo.videorentalservice.business.rental.service;

import com.casumo.videorentalservice.model.entity.CustomerEntity;

public interface CustomerService {

  /**
   * Retrieves the current customer entity.
   *
   * @return The current customer as a {@link CustomerEntity} object.
   */
  CustomerEntity getCurrentCustomer();
}
