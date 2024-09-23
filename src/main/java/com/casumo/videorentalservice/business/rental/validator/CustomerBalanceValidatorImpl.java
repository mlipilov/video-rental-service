package com.casumo.videorentalservice.business.rental.validator;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculator;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerBalanceValidatorImpl implements CustomerBalanceValidator {

  private final MovieRentalPriceCalculator movieRentalPriceCalculator;

  @Override
  public void validate(final CustomerEntity customer, final MovieEntity movie) {
    final BigDecimal customerBalance = customer.getBalance();
    final BigDecimal currentRentalPrice = movieRentalPriceCalculator.calculate(movie);

    if (customerBalance.compareTo(currentRentalPrice) < INTEGER_ZERO) {
      throw new RuntimeException();
    }
  }
}
