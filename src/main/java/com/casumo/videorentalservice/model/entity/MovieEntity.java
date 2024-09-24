package com.casumo.videorentalservice.model.entity;

import static jakarta.persistence.EnumType.STRING;

import com.casumo.videorentalservice.model.enumuration.MovieType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class MovieEntity extends IdentifiableEntity {

  @Column(name = "movie_title", unique = true, nullable = false)
  private String movieTitle;

  @Enumerated(STRING)
  @Column(name = "movie_type", nullable = false)
  private MovieType movieType;

  @Column(name = "count", nullable = false)
  private Integer count;

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
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
        : this.getClass();

    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }

    final MovieEntity that = (MovieEntity) o;
    return getMovieTitle() != null
        && Objects.equals(getMovieTitle(), that.getMovieTitle());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }

  public void decreaseCount() {
    this.count--;
  }
}
