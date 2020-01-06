package com.places.server.service.impl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.places.server.entity.AbstractEntity;
import com.places.server.repository.AppRepository;
import com.places.server.service.AppService;

abstract class AbstractServiceImpl<T extends AbstractEntity> extends RemoteServiceServlet implements AppService<T> {

  @Override
  public T save(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T findById(Long id) {
    return getRepository().findById(id);
  }

  @Override
  public boolean delete(T entity) {
    return getRepository().delete(entity);
  }

  abstract AppRepository<T> getRepository();
}
