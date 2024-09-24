package com.casumo.videorentalservice.business.rental.facade;

import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;

public interface RentMovieFacade {

  /**
   * Processes the rental of one or more movies based on the provided rental requests.
   *
   * @param rentalRequests a list of {@link RentMovieRq} objects that contain the movie IDs and the
   *                       number of rental days for each movie.
   * @return a {@link MovieRentedRs} object that provides information about the rented movies,
   * including rental details and the total rental price.
   */
  MovieRentedRs rentMovies(List<RentMovieRq> rentalRequests);
}
