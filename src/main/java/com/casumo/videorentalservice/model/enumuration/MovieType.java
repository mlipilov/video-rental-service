package com.casumo.videorentalservice.model.enumuration;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieType {

  NEW(BigDecimal.valueOf(40L, 2), "New Release"),
  REGULAR(BigDecimal.valueOf(30L, 2), "Regular"),
  OLD(BigDecimal.valueOf(30L, 2), "Old Release");

  private final BigDecimal rentalChargeAmount;
  private final String displayName;
}
