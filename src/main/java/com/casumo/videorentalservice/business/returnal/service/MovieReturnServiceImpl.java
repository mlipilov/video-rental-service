package com.casumo.videorentalservice.business.returnal.service;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;

import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.LongStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieReturnServiceImpl implements MovieReturnService {

  @Override
  @Transactional
  public Set<RentalEntity> returnMovies(
      final Set<RentalEntity> rentals,
      final CustomerEntity currentCustomer
  ) {
    rentals.forEach(rental -> {
      final Integer rentalDays = rental.getRentalDays();
      final LocalDate rentedAt = rental.getRentedAt();
      final LocalDate currentDate = LocalDate.now();
      final MovieEntity movie = rental.getMovie();
      final LocalDate returnDeadline = rentedAt.plusDays(rentalDays);

      if (currentDate.isAfter(returnDeadline)) {
        final long extraDays = ChronoUnit.DAYS.between(returnDeadline, currentDate);
        final BigDecimal extraFee = LongStream.range(LONG_ZERO, extraDays)
            .mapToObj(i -> movie.getMovieType().getRentalChargeAmount())
            .reduce(ZERO, BigDecimal::add);

        currentCustomer.payForMovieRent(extraFee);
        rental.setExtraFee(extraFee);
      }

      movie.increaseCount();
      rental.setReturned(true);
    });

    return rentals;
  }
}
