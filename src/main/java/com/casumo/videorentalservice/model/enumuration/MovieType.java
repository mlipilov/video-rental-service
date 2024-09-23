package com.casumo.videorentalservice.model.enumuration;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieType {

  NEW(BigDecimal.valueOf(40L, 2)),
  REGULAR(BigDecimal.valueOf(30L, 2)),
  OLD(BigDecimal.valueOf(30L, 2));

  private final BigDecimal rentalChargeAmount;
}
