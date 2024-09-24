package com.casumo.videorentalservice.business.rental.mapper.impl;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.response.GetRentalInfoRs;
import com.casumo.videorentalservice.model.response.GetReturnInfoRs;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import com.casumo.videorentalservice.model.response.MovieReturnedRs;
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
    rs.setTotalRentalPrice(totalRentalPrice);

    rs.setRentalInfo(toGetRentalInfoRs(rentals));
    return rs;
  }

  @Override
  public MovieReturnedRs toMovieReturnedRs(final Set<RentalEntity> returnedRentals) {
    final MovieReturnedRs rs = new MovieReturnedRs();

    final BigDecimal totalLateCharge = returnedRentals.stream()
        .map(RentalEntity::getExtraFee)
        .reduce(BigDecimal::add)
        .orElse(null);
    rs.setTotalLateCharge(totalLateCharge);

    rs.setReturnInfo(toGetReturnInfoRs(returnedRentals));
    return rs;
  }

  private List<GetReturnInfoRs> toGetReturnInfoRs(final Set<RentalEntity> returnedRentals) {
    return returnedRentals.stream()
        .map(this::toGetReturnInfoRsItem)
        .toList();
  }

  private GetReturnInfoRs toGetReturnInfoRsItem(final RentalEntity rentalEntity) {
    final MovieEntity movie = rentalEntity.getMovie();

    final GetReturnInfoRs returnInfoRs = new GetReturnInfoRs();
    returnInfoRs.setMovieId(movie.getId());
    returnInfoRs.setMovieType(movie.getMovieType().getDisplayName());
    returnInfoRs.setMovieTitle(movie.getMovieTitle());
    returnInfoRs.setRentalDays(rentalEntity.getRentalDays());
    returnInfoRs.setRentedAt(rentalEntity.getRentedAt());
    returnInfoRs.setPaidRentalPrice(rentalEntity.getRentalPrice());
    returnInfoRs.setExtraRentalPrice(rentalEntity.getExtraFee());

    return returnInfoRs;
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
    rentalInfoRs.setRentalPrice(rentalEntity.getRentalPrice());

    return rentalInfoRs;
  }
}
