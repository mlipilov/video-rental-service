package com.casumo.videorentalservice.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class GetReturnInfoRs {

  private Long movieId;
  private String movieTitle;
  private String movieType;
  private Integer rentalDays;
  private Integer extraRentalDays;
  private LocalDate rentedAt;
  private BigDecimal paidRentalPrice;
  private BigDecimal extraRentalPrice;
}
