package com.casumo.videorentalservice.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class MovieRentedRs {

  private List<GetRentalInfoRs> rentalInfo;
  private BigDecimal rentalPrice;
}
