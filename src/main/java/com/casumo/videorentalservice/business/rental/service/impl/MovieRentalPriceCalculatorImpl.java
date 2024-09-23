package com.casumo.videorentalservice.business.rental.service.impl;

import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculator;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class MovieRentalPriceCalculatorImpl implements MovieRentalPriceCalculator {

  @Override
  public BigDecimal calculate(final MovieEntity movie) {
    return null;
  }
}
