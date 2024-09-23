package com.casumo.videorentalservice.model.request;

import lombok.Data;

@Data
public class RentMovieRq {

  private Long movieId;
  private Integer rentalDays;
}
