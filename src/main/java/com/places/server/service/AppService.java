package com.places.server.service;

import com.places.server.entity.AbstractEntity;
import java.util.List;

public interface AppService<T extends AbstractEntity> {

  void save(T entity);

  T findById(Long id);

  List<T> findAll();

  boolean delete(T entity);
}
