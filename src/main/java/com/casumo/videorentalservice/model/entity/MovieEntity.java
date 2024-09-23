package com.casumo.videorentalservice.model.entity;

import static jakarta.persistence.EnumType.STRING;

import com.casumo.videorentalservice.model.enumuration.MovieType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class MovieEntity extends IdentifiableEntity {

  @Column(name = "movie_title")
  private String movieTitle;

  @Enumerated(STRING)
  @Column(name = "movie_type")
  private MovieType movieType;
}
