package com.places.server.repository.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.places.server.entity.AbstractEntity;
import com.places.server.repository.AppRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

abstract class AbstractRepositoryImpl<T extends AbstractEntity> implements AppRepository<T> {

  @Inject
  protected Provider<EntityManager> entityManagerProvider;

  @Override
  @Transactional
  public T save(T entity) {
    return entityManagerProvider.get().merge(entity);
  }

  @Override
  public List<T> save(List<T> entities) {
    List<T> result = new ArrayList<>();
    for (T entity : entities) {
      result.add(this.save(entity));
    }
    return result;
  }

  @Override
  public T findById(Long id) {
    final EntityManager entityManager = entityManagerProvider.get();
    return entityManager.find(getClassType(), id);
  }

  @Override
  @Transactional
  public boolean delete(T entity) {
    T savedEntity = this.findById(entity.getId());
    if (savedEntity == null) {
      return false;
    }
    final EntityManager entityManager = entityManagerProvider.get();
    entityManager.remove(savedEntity);
    return true;
  }

  abstract Class<T> getClassType();
}
