package com.places.server.repository;

import com.places.server.entity.AbstractEntity;
import java.util.List;

public interface AppRepository<T extends AbstractEntity> {

  T save(T entity);

  T findById(Long id);

  List<T> findAll();

  boolean delete(T entity);
}
