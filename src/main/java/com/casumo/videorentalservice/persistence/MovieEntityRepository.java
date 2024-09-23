package com.casumo.videorentalservice.persistence;

import com.casumo.videorentalservice.model.entity.MovieEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long> {

  List<MovieEntity> findByIdIn(Collection<Long> ids);
}