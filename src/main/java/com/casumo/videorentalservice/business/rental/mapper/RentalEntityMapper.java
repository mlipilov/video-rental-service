package com.casumo.videorentalservice.business.rental.mapper;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import com.casumo.videorentalservice.model.entity.RentalEntity;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.math.BigDecimal;
import java.util.Set;
import org.springframework.http.ResponseEntity;

public interface RentalEntityMapper {

  /**
   * Converts a MovieEntity instance to a RentalEntity instance.
   *
   * @param movie       The movie entity to be converted.
   * @param rentalDays  Rental days
   * @param rentalPrice Price of the rent
   * @return A new RentalEntity instance based on the given MovieEntity.
   */
  RentalEntity toRental(MovieEntity movie, Integer rentalDays, BigDecimal rentalPrice);

  /**
   * Converts a set of RentalEntity objects into a ResponseEntity containing a MovieRentedRs
   * object.
   *
   * @param rentals A set of RentalEntity objects representing movies that have been rented.
   * @return A ResponseEntity containing a MovieRentedRs object which includes rental information
   * and the total rental price.
   */
  MovieRentedRs toMovieRentedRs(Set<RentalEntity> rentals);
}
