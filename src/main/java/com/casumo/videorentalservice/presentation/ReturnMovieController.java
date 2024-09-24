package com.casumo.videorentalservice.presentation;

import static org.springframework.http.HttpStatus.OK;

import com.casumo.videorentalservice.business.returnal.facade.ReturnMovieFacade;
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
public class ReturnMovieController {

  private final ReturnMovieFacade returnMovieFacade;

  @PatchMapping("/return")
  ResponseEntity<MovieRentedRs> rentMovies(@RequestBody final List<Long> returnRq) {
    return new ResponseEntity<>(returnMovieFacade.returnMovies(returnRq), OK);
  }
}
