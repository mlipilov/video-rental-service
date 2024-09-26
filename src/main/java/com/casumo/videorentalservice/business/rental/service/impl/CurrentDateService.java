package com.casumo.videorentalservice.business.rental.service.impl;

import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class CurrentDateService {

  public LocalDate getCurrentDate() {
    return LocalDate.now();
  }
}
