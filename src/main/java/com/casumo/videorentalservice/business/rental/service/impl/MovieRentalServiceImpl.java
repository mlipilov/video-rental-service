package com.casumo.videorentalservice.business.rental.service.impl;

import com.casumo.videorentalservice.business.rental.service.CustomerService;
import com.casumo.videorentalservice.business.rental.service.MovieService;
import com.casumo.videorentalservice.business.rental.service.MovieRentalCheckoutService;
import com.casumo.videorentalservice.business.rental.service.MovieRentalService;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieRentalServiceImpl implements MovieRentalService {

  private final CustomerService customerService;
  private final MovieService movieService;
  private final MovieRentalCheckoutService movieRentalCheckoutService;

  @Override
  @Transactional
  public Set<RentalEntity> createRental(final List<RentMovieRq> rentalRequests) {
    log.info("Started creating rentals...");
    //get current customer
    final CustomerEntity customer = customerService.getCurrentCustomer();
    //get movies to rental days map
    final Map<MovieEntity, Integer> moviesMap = movieService.getMoviesForRental(rentalRequests);
    //pay and rent
    movieRentalCheckoutService.payAndRent(moviesMap, customer);
    //return rentals
    return customer.getRentals();
  }
}
