package com.casumo.videorentalservice.business.rental.mapper;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import com.casumo.videorentalservice.model.response.MovieReturnedRs;
import java.math.BigDecimal;
import java.util.Set;

public interface RentalEntityMapper {

  /**
   * Converts a MovieEntity object into a RentalEntity object with specified rental days and price.
   *
   * @param movie       The MovieEntity object representing the movie to be rented.
   * @param rentalDays  The number of days the movie is being rented for.
   * @param rentalPrice The price of renting the movie for the specified number of days.
   * @return A RentalEntity object containing rental details for the specified movie.
   */
  RentalEntity toRental(MovieEntity movie, Integer rentalDays, BigDecimal rentalPrice);

  /**
   * Converts a set of {@link RentalEntity} objects into a {@link MovieRentedRs} object.
   *
   * @param rentals the set of {@link RentalEntity} objects representing the rental details.
   * @return a {@link MovieRentedRs} object containing the rental information and the total rental
   * price.
   */
  MovieRentedRs toMovieRentedRs(Set<RentalEntity> rentals);

  /**
   * Converts a set of returned {@link RentalEntity} objects into a {@link MovieReturnedRs} object.
   *
   * @param returnedRentals the set of {@link RentalEntity} objects that have been returned.
   * @return a {@link MovieReturnedRs} object containing the details of the returned movies and the
   * total late charge.
   */
  MovieReturnedRs toMovieReturnedRs(Set<RentalEntity> returnedRentals);
}
