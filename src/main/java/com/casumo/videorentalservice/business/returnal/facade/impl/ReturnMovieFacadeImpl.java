package com.casumo.videorentalservice.business.returnal.facade.impl;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.CustomerService;
import com.casumo.videorentalservice.business.returnal.facade.ReturnMovieFacade;
import com.casumo.videorentalservice.business.returnal.service.MovieReturnService;
import com.casumo.videorentalservice.common.annotation.Facade;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.response.MovieReturnedRs;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Facade
@RequiredArgsConstructor
public class ReturnMovieFacadeImpl implements ReturnMovieFacade {

  private final CustomerService customerService;
  private final MovieReturnService movieReturnService;
  private final RentalEntityMapper rentalEntityMapper;

  @Override
  @Transactional
  public MovieReturnedRs returnMovies(final List<Long> returnRq) {
    log.info("Started returning movies...");
    final CustomerEntity currentCustomer = customerService.getCurrentCustomer();
    final Set<RentalEntity> rentals = currentCustomer.getRentals();
    final Set<RentalEntity> returnedRentals = movieReturnService.returnMovies(
        rentals,
        currentCustomer);
    return rentalEntityMapper.toMovieReturnedRs(returnedRentals);
  }
}
