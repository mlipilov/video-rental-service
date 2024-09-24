package com.casumo.videorentalservice.persistence;

import com.casumo.videorentalservice.model.entity.CustomerEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

  @EntityGraph(value = "rentals")
  Optional<CustomerEntity> findByIdentityProviderId(String identityProviderId);
}