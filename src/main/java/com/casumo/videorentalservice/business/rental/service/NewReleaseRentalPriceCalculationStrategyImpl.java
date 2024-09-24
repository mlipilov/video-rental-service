package com.casumo.videorentalservice.business.rental.service;

import static com.casumo.videorentalservice.model.enumuration.MovieType.NEW;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class NewReleaseRentalPriceCalculationStrategyImpl
    implements MovieRentalPriceCalculationStrategy {

  @Override
  public BigDecimal calculate(final MovieEntity movie, final Integer rentalDays) {
    return IntStream.range(INTEGER_ZERO, rentalDays)
        .mapToObj(i -> movie.getMovieType().getRentalChargeAmount())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public String getStrategyName() {
    return NEW.getDisplayName();
  }
}
