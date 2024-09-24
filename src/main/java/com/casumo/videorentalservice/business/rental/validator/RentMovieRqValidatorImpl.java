package com.casumo.videorentalservice.business.rental.validator;

import static java.util.Objects.isNull;

import com.casumo.videorentalservice.common.annotation.Validator;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class RentMovieRqValidatorImpl implements RentMovieRqValidator {

  @Override
  public void validate(final List<RentMovieRq> rentalRequests) {
    final boolean isRqNotValid = rentalRequests.stream()
        .anyMatch(rq -> isNull(rq.getRentalDays()) || isNull(rq.getMovieId()));

    if (isRqNotValid) {
      throw new RuntimeException("Request is not valid!");
    }
  }
}
