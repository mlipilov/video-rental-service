package com.casumo.videorentalservice.common.config;

import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculationStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MovieRentalPriceCalculationStrategyConfig {

  @Bean
  @Primary
  public Map<String, MovieRentalPriceCalculationStrategy> calculationStrategyMap(
      final List<MovieRentalPriceCalculationStrategy> strategies
  ) {
    final Map<String, MovieRentalPriceCalculationStrategy> strategyMap = new HashMap<>();
    strategies.forEach(strategy -> strategyMap.put(strategy.getStrategyName(), strategy));
    return strategyMap;
  }
}
