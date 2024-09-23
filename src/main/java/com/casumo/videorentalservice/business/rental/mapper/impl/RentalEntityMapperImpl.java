package com.casumo.videorentalservice.business.rental.mapper.impl;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.response.GetRentalInfoRs;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class RentalEntityMapperImpl implements RentalEntityMapper {

  @Override
  public RentalEntity toRental(
      final MovieEntity movie,
      final Integer rentalDays,
      final BigDecimal rentalPrice
  ) {
    final RentalEntity rental = new RentalEntity();
    rental.setRentedAt(LocalDate.now());
    rental.setRentalDays(rentalDays);
    rental.setMovie(movie);
    rental.setRentalPrice(rentalPrice);
    return rental;
  }

  @Override
  public MovieRentedRs toMovieRentedRs(final Set<RentalEntity> rentals) {
    final MovieRentedRs rs = new MovieRentedRs();

    final BigDecimal totalRentalPrice = rentals.stream()
        .map(RentalEntity::getRentalPrice)
        .reduce(BigDecimal::add)
        .orElse(null);
    rs.setRentalPrice(totalRentalPrice);

    rs.setGetRentalInfoRs(toGetRentalInfoRs(rentals));
    return rs;
  }

  private List<GetRentalInfoRs> toGetRentalInfoRs(final Set<RentalEntity> rentals) {
    return rentals.stream()
        .map(this::toGetRentalInfoRsItem)
        .toList();
  }

  private GetRentalInfoRs toGetRentalInfoRsItem(final RentalEntity rentalEntity) {
    final MovieEntity movie = rentalEntity.getMovie();

    final GetRentalInfoRs rentalInfoRs = new GetRentalInfoRs();
    rentalInfoRs.setMovieId(movie.getId());
    rentalInfoRs.setMovieType(movie.getMovieType().getDisplayName());
    rentalInfoRs.setMovieTitle(movie.getMovieTitle());
    rentalInfoRs.setRentalDays(rentalEntity.getRentalDays());
    rentalInfoRs.setRentedAt(rentalEntity.getRentedAt());

    return rentalInfoRs;
  }
}
