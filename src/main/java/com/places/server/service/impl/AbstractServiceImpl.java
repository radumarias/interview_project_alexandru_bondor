package com.places.server.service.impl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.places.server.entity.AbstractEntity;
import com.places.server.repository.AppRepository;
import com.places.server.service.AppService;
import java.util.List;

abstract class AbstractServiceImpl<T extends AbstractEntity> extends RemoteServiceServlet implements AppService<T> {

  @Override
  public void save(T entity) {
    getRepository().save(entity);
  }

  @Override
  public T findById(Long id) {
    return getRepository().findById(id);
  }

  @Override
  public List<T> findAll() {
    return getRepository().findAll();
  }

  @Override
  public boolean delete(T entity) {
    return getRepository().delete(entity);
  }

  abstract AppRepository<T> getRepository();
}
