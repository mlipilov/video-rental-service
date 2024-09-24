package com.casumo.videorentalservice.model.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.proxy.HibernateProxy;


@Getter
@Setter
@Entity
@Table(name = "customers")
@NamedEntityGraph(
    name = "rentals",
    attributeNodes = @NamedAttributeNode(value = "rentals"))
public class CustomerEntity extends IdentifiableEntity {

  @Column(name = "identity_provider_id", nullable = false)
  private String identityProviderId;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "balance", nullable = false, scale = 2)
  private BigDecimal balance;

  @OneToMany(mappedBy = "customer", cascade = {PERSIST, MERGE})
  @BatchSize(size = 200)
  private Set<RentalEntity> rentals = new HashSet<>();

  public void addRental(@NonNull final RentalEntity rental) {
    rental.setCustomer(this);
    this.rentals.add(rental);
  }

  public void payForMovieRent(@NonNull final BigDecimal price) {
    if (this.balance.compareTo(price) < INTEGER_ZERO) {
      throw new RuntimeException("Inefficient funds!");
    }

    this.balance = this.balance.subtract(price);
  }

  @Override
  public final boolean equals(final Object o) {
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

    final CustomerEntity customer = (CustomerEntity) o;
    return getIdentityProviderId() != null
        && Objects.equals(getIdentityProviderId(), customer.getIdentityProviderId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
