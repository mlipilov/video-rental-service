package com.casumo.videorentalservice;

import com.casumo.videorentalservice.utils.VideoRentalQueryExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseIntegrationTest {

  @Autowired
  protected VideoRentalQueryExecutionListener queryExecutionListener;
}
