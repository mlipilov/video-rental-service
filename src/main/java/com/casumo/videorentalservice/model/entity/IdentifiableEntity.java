package com.casumo.videorentalservice.model.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class IdentifiableEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
}
