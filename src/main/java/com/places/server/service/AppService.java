package com.places.server.service;

import com.places.server.entity.AbstractEntity;

public interface AppService<T extends AbstractEntity> {

  T save(T entity);

  T findById(Long id);

  boolean delete(T entity);
}
