package com.casumo.videorentalservice.business.returnal.facade.impl;

import com.casumo.videorentalservice.business.returnal.facade.ReturnMovieFacade;
import com.casumo.videorentalservice.common.annotation.Facade;
import com.casumo.videorentalservice.model.response.MovieRentedRs;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Facade
@RequiredArgsConstructor
public class ReturnMovieFacadeImpl implements ReturnMovieFacade {

  @Override
  public MovieRentedRs returnMovies(final List<Long> returnRq) {
    return null;
  }
}
