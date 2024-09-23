package com.casumo.videorentalservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity extends IdentifiableEntity {

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "balance", nullable = false, scale = 2)
  private BigDecimal balance;

  @OneToMany(mappedBy = "customer")
  private List<RentalEntity> rentals = new ArrayList<>();
}
