package com.casumo.videorentalservice.business.rental.service.impl;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieEntityRepository movieEntityRepository;

  @Override
  public Map<MovieEntity, Integer> getMoviesToRentalDays(final List<RentMovieRq> rentalRequests) {
    log.info("Started getting movies to rental days...");
    final Map<Long, Integer> movieIdToRentalDaysMap = rentalRequests.stream()
        .collect(Collectors.toMap(
            RentMovieRq::getMovieId,
            RentMovieRq::getRentalDays));
    final Set<Long> movieIds = movieIdToRentalDaysMap.keySet();

    final List<MovieEntity> movies = movieEntityRepository.findByIdIn(movieIds);
    return movies.stream().collect(Collectors.toMap(movie -> movie, movieIdToRentalDaysMap::get));
  }
}
