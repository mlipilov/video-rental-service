package com.casumo.videorentalservice.model.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class GetRentalInfoRs {

  private Long movieId;
  private String movieTitle;
  private String movieType;
  private Integer rentalDays;
  private LocalDate rentedAt;
}
