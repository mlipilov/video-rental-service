package com.casumo.videorentalservice.business.rental.service.impl;

import static com.casumo.videorentalservice.common.constant.MdcKeyConstants.USER_ID;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.MovieRentalPriceCalculator;
import com.casumo.videorentalservice.business.rental.service.RentalService;
import com.casumo.videorentalservice.business.rental.validator.CustomerBalanceValidator;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.persistence.CustomerEntityRepository;
import com.casumo.videorentalservice.persistence.MovieEntityRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

  private final CustomerEntityRepository customerRepository;
  private final MovieEntityRepository movieEntityRepository;
  private final RentalEntityMapper rentalEntityMapper;
  private final MovieRentalPriceCalculator movieRentalPriceCalculator;

  @Override
  @Transactional
  public Set<RentalEntity> createRental(final List<RentMovieRq> rentalRequests) {
    //get current customer
    final String userId = MDC.get(USER_ID);
    final CustomerEntity customer = customerRepository.findByIdentityProviderId(userId)
        .orElseThrow();

    //get movies for rental
    final Map<Long, Integer> movieIdToRentalDaysMap = rentalRequests.stream()
        .collect(Collectors.toMap(
            RentMovieRq::getMovieId,
            RentMovieRq::getRentalDays));
    final Set<Long> movieIds = movieIdToRentalDaysMap.keySet();
    final List<MovieEntity> movies = movieEntityRepository.findByIdIn(movieIds);

    //pay and rent
    movies.forEach(movie -> {
      final BigDecimal rentalPrice = movieRentalPriceCalculator.calculate(movie);
      customer.payForMovieRent(rentalPrice);

      final Integer rentalDays = movieIdToRentalDaysMap.get(movie.getId());
      final RentalEntity rental = rentalEntityMapper.toRental(movie, rentalDays, rentalPrice);
      customer.addRental(rental);
    });

    //save changes
    customerRepository.save(customer);
    return customer.getRentals();
  }
}
