package com.places.server.repository.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.places.server.entity.AbstractEntity;
import com.places.server.repository.AppRepository;
import java.util.List;
import javax.persistence.EntityManager;

abstract class AbstractRepositoryImpl<T extends AbstractEntity> implements AppRepository<T> {

  @Inject
  private Provider<EntityManager> entityManagerProvider;

  @Override
  public T save(T entity) {
    return entityManagerProvider.get().merge(entity);
  }

  @Override
  public T findById(Long id) {
    final EntityManager entityManager = entityManagerProvider.get();
    return entityManager.find(getClassType(), id);
  }

  @Override
  public List<T> findAll() {
    return null;
  }

  @Override
  public boolean delete(T entity) {
    return false;
  }

  abstract Class<T> getClassType();
}
