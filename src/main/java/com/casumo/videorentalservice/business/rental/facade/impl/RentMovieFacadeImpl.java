package com.casumo.videorentalservice.business.rental.facade.impl;

import com.casumo.videorentalservice.business.rental.facade.RentMovieFacade;
import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.RentalService;
import com.casumo.videorentalservice.business.rental.validator.RentMovieRqValidator;
import com.casumo.videorentalservice.common.annotation.Facade;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class RentMovieFacadeImpl implements RentMovieFacade {

  private final RentMovieRqValidator rentMovieRqValidator;
  private final RentalService rentalService;
  private final RentalEntityMapper rentalEntityMapper;

  @Override
  public MovieRentedRs rentMovies(final List<RentMovieRq> rentalRequests) {
    rentMovieRqValidator.validate(rentalRequests);
    final Set<RentalEntity> rentals = rentalService.createRental(rentalRequests);
    return rentalEntityMapper.toMovieRentedRs(rentals);
  }
}
