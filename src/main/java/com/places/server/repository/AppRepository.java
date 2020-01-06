package com.places.server.repository;

import com.places.server.entity.AbstractEntity;
import java.util.List;

public interface AppRepository<T extends AbstractEntity> {

  T save(T entity);

  List<T> save(List<T> entities);

  T findById(Long id);

  boolean delete(T entity);
}
