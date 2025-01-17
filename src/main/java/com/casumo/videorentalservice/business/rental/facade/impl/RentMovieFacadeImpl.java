package com.casumo.videorentalservice.business.rental.facade.impl;

import com.casumo.videorentalservice.business.rental.facade.RentMovieFacade;
import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.MovieRentalService;
import com.casumo.videorentalservice.business.rental.validator.RentMovieRqValidator;
import com.casumo.videorentalservice.common.annotation.Facade;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Facade
@RequiredArgsConstructor
public class RentMovieFacadeImpl implements RentMovieFacade {

  private final RentMovieRqValidator rentMovieRqValidator;
  private final MovieRentalService movieRentalService;
  private final RentalEntityMapper rentalEntityMapper;

  @Override
  @Transactional
  public MovieRentedRs rentMovies(final List<RentMovieRq> rentalRequests) {
    rentMovieRqValidator.validate(rentalRequests);
    final Set<RentalEntity> rentals = movieRentalService.createRental(rentalRequests);
    final MovieRentedRs rs = rentalEntityMapper.toMovieRentedRs(rentals);
    log.info("Successfully rented {} movies", rs.getRentalInfo().size());
    return rs;
  }
}
