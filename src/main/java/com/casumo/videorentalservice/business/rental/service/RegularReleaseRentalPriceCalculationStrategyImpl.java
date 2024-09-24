package com.casumo.videorentalservice.business.rental.service;

import static com.casumo.videorentalservice.model.enumuration.MovieType.REGULAR;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class RegularReleaseRentalPriceCalculationStrategyImpl
    implements MovieRentalPriceCalculationStrategy {

  private static final Integer NO_ADDITIONAL_CHARGE_DAYS_AMOUNT = 3;

  @Override
  public BigDecimal calculate(final MovieEntity movie, final Integer rentalDays) {
    if (rentalDays <= NO_ADDITIONAL_CHARGE_DAYS_AMOUNT) {
      return movie.getMovieType().getRentalChargeAmount();
    } else {
      final int additionCounter = rentalDays - NO_ADDITIONAL_CHARGE_DAYS_AMOUNT;

      return IntStream.range(INTEGER_ZERO, additionCounter)
          .mapToObj(i -> movie.getMovieType().getRentalChargeAmount())
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
  }

  @Override
  public String getStrategyName() {
    return REGULAR.getDisplayName();
  }
}
