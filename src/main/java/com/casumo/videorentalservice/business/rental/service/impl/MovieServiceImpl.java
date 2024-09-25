package com.casumo.videorentalservice.business.rental.service.impl;

import static java.util.function.Function.identity;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.casumo.videorentalservice.business.rental.service.MovieService;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.persistence.MovieEntityRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieEntityRepository movieEntityRepository;

  @Override
  @Transactional
  public Map<MovieEntity, Integer> getMoviesForRental(final List<RentMovieRq> rentalRequests) {
    log.info("Started getting movies to rental days...");
    final Map<Long, Integer> movieIdToRentalDaysMap = rentalRequests.stream()
        .collect(Collectors.toMap(
            RentMovieRq::getMovieId,
            RentMovieRq::getRentalDays));
    final Set<Long> movieIds = movieIdToRentalDaysMap.keySet();

    final List<MovieEntity> movies = movieEntityRepository.findByIdIn(movieIds);
    final boolean isAnyUnavailable = movies.stream()
        .anyMatch(movie -> INTEGER_ZERO.equals(movie.getCount()));
    if (isAnyUnavailable) {
      throw new RuntimeException("Some films you chose are not available!");
    }

    return movies.stream().collect(Collectors.toMap(
        identity(),
        v -> movieIdToRentalDaysMap.get(v.getId())));
  }
}
