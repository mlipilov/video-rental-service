package com.casumo.videorentalservice.model.enumuration;

import static java.math.RoundingMode.UNNECESSARY;
import static org.apache.commons.lang3.math.NumberUtils.toScaledBigDecimal;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieType {

  NEW(toScaledBigDecimal("40.00", 2, UNNECESSARY), "New Release"),
  REGULAR(toScaledBigDecimal("30.00", 2, UNNECESSARY), "Regular"),
  OLD(toScaledBigDecimal("30.00", 2, UNNECESSARY), "Old Release");

  private final BigDecimal rentalChargeAmount;
  private final String displayName;
}
