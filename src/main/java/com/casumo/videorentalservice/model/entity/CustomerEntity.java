package com.casumo.videorentalservice.model.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity extends IdentifiableEntity {

  @Column(name = "identity_provider_id")
  private String identityProviderId;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "balance", nullable = false, scale = 2)
  private BigDecimal balance;

  @OneToMany(mappedBy = "customer")
  private Set<RentalEntity> rentals = new HashSet<>();

  public void addRental(@NonNull final RentalEntity rental) {
    this.rentals.add(rental);
    rental.setCustomer(this);
  }

  public void payForMovieRent(@NonNull final BigDecimal price) {
    if (this.balance.compareTo(price) < INTEGER_ZERO) {
      throw new RuntimeException("Inefficient funds!");
    }

    this.balance = this.balance.subtract(price);
  }
}
