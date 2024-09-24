package com.casumo.videorentalservice.presentation;

import static org.springframework.http.HttpStatus.OK;

import com.casumo.videorentalservice.business.rental.facade.RentMovieFacade;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class RentMovieController {

  private final RentMovieFacade rentMovieFacade;

  @PatchMapping("/rent")
  ResponseEntity<MovieRentedRs> rentMovies(@RequestBody final List<RentMovieRq> rentalRequests) {
    return new ResponseEntity<>(rentMovieFacade.rentMovies(rentalRequests), OK);
  }
}
