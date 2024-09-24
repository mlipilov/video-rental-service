package com.casumo.videorentalservice.business.rental.service.impl;

import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculationStrategy;
import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculator;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MovieRentalPriceCalculatorImpl implements MovieRentalPriceCalculator {

  private final Map<String, MovieRentalPriceCalculationStrategy> strategyMap;

  public MovieRentalPriceCalculatorImpl(
      @Qualifier("calculationStrategyMap")
      final Map<String, MovieRentalPriceCalculationStrategy> strategyMap
  ) {
    this.strategyMap = strategyMap;
  }

  @Override
  public BigDecimal calculate(
      @NonNull final MovieEntity movie,
      @NonNull final Integer rentalDays
  ) {
    final String movieType = movie.getMovieType().getDisplayName();
    final MovieRentalPriceCalculationStrategy strategy = strategyMap.get(movieType);
    return strategy.calculate(movie, rentalDays);
  }
}
