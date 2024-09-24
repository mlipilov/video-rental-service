package com.casumo.videorentalservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rentals")
public class RentalEntity extends IdentifiableEntity {

  @Column(name = "rented_at")
  private LocalDate rentedAt;

  @Column(name = "rental_days")
  private Integer rentalDays;

  @Column(name = "rental_price", scale = 2)
  private BigDecimal rentalPrice;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieEntity movie;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final RentalEntity that = (RentalEntity) o;
    return Objects.equals(customer, that.customer)
        && Objects.equals(movie, that.movie);
  }

  @Override
  public int hashCode() {
    return customer.hashCode() + movie.hashCode();
  }
}
