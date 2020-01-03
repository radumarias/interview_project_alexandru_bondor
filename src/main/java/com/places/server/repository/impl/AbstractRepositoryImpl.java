package com.places.server.repository.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.places.server.entity.AbstractEntity;
import com.places.server.repository.AppRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    final CriteriaQuery<T> createQuery = entityManager.getCriteriaBuilder().createQuery(getClassType());
    final Root<T> root = createQuery.from(getClassType());
    createQuery.where(root.get("id").in(id));
    return entityManager.createQuery(createQuery).getSingleResult();
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
