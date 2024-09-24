package com.casumo.videorentalservice.business.rental.service.impl;

import static com.casumo.videorentalservice.common.constant.MdcKeyConstants.USER_ID;

import com.casumo.videorentalservice.business.rental.service.CustomerService;
import com.casumo.videorentalservice.model.entity.CustomerEntity;
import com.casumo.videorentalservice.persistence.CustomerEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerEntityRepository customerRepository;

  @Override
  @Transactional
  public CustomerEntity getCurrentCustomer() {
    log.info("Started getting current customer...");
    final String userId = MDC.get(USER_ID);
    return customerRepository.findByIdentityProviderId(userId)
        .orElseThrow(() -> new RuntimeException("No such customer is present!"));
  }
}
