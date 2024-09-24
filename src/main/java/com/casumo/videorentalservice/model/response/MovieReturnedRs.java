package com.casumo.videorentalservice.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class MovieReturnedRs {

  private List<GetReturnInfoRs> returnInfo;
  private BigDecimal totalLateCharge;
}
