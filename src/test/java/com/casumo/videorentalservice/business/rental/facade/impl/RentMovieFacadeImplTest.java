package com.casumo.videorentalservice.business.rental.facade.impl;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.casumo.videorentalservice.business.rental.mapper.RentalEntityMapper;
import com.casumo.videorentalservice.business.rental.service.RentalService;
import com.casumo.videorentalservice.business.rental.validator.RentMovieRqValidator;
import com.casumo.videorentalservice.model.request.RentMovieRq;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentMovieFacadeImplTest {

  @Mock
  private RentMovieRqValidator rentMovieRqValidator;
  @Mock
  private RentalService rentalService;
  @Mock
  private RentalEntityMapper rentalEntityMapper;

  @InjectMocks
  private RentMovieFacadeImpl rentMovieFacade;

  @Test
  void whenRentMovies_ThenReturnMovieRentedRs() {
    final MovieRentedRs movieRentedRs = new MovieRentedRs();
    movieRentedRs.setRentalInfo(List.of());

    when(rentalService.createRental(anyList())).thenReturn(new HashSet<>());
    when(rentalEntityMapper.toMovieRentedRs(anySet())).thenReturn(movieRentedRs);

    final MovieRentedRs rs = rentMovieFacade.rentMovies(List.of(new RentMovieRq()));

    verify(rentMovieRqValidator, times(INTEGER_ONE)).validate(anyList());
    assertNotNull(rs);
  }
}