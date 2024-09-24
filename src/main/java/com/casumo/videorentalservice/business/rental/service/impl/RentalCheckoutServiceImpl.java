package com.casumo.videorentalservice.business.rental.service.impl;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculator;
import com.casumo.videorentalservice.business.rental.service.RentalCheckoutService;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import java.math.BigDecimal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalCheckoutServiceImpl implements RentalCheckoutService {

  private final RentalEntityMapper rentalEntityMapper;
  private final MovieRentalPriceCalculator movieRentalPriceCalculator;

  @Override
  @Transactional
  public void payAndRent(
      final Map<MovieEntity, Integer> moviesMap,
      final CustomerEntity customer
  ) {
    log.info("Started paying and renting movies...");
    moviesMap.forEach((movie, rentalDays) -> {
      movie.decreaseCount();

      final BigDecimal rentalPrice = movieRentalPriceCalculator.calculate(movie, rentalDays);
      customer.payForMovieRent(rentalPrice);

      final RentalEntity rental = rentalEntityMapper.toRental(movie, rentalDays, rentalPrice);
      customer.addRental(rental);
    });
  }
}
