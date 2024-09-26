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
import org.hibernate.proxy.HibernateProxy;

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

  @Column(name = "is_returned")
  private boolean isReturned;

  @Column(name = "extra_fee", scale = 2)
  private BigDecimal extraFee;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  private MovieEntity movie;

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }

    final Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    final Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();

    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }

    final RentalEntity that = (RentalEntity) o;
    return Objects.equals(getRentalDays(), that.rentalDays)
        && Objects.equals(customer, that.customer)
        && Objects.equals(movie, that.movie);
  }

  @Override
  public int hashCode() {
    return customer.hashCode() + movie.hashCode() + rentalDays.hashCode();
  }
}
