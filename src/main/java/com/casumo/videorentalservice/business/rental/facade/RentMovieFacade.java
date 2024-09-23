package com.casumo.videorentalservice.business.rental.facade;

import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;

public interface RentMovieFacade {

  /**
   * Rents a list of movies based on the provided rental requests.
   *
   * @param rentalRequests A list of rental requests where each request includes a movie ID and the
   *                       number of rental days.
   * @return A ResponseEntity containing a MovieRentedRs object which includes rental information
   * and the total rental price.
   */
  MovieRentedRs rentMovies(List<RentMovieRq> rentalRequests);
}
