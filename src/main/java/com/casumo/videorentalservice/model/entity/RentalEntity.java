package com.casumo.videorentalservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieEntity movie;
}
