package com.places.server.config.servlet;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class AppServiceLocator implements ServiceLocator {

  private Injector injector;

  @Inject
  public AppServiceLocator(final Injector injector) {
    this.injector = injector;
  }

  @Override
  public Object getInstance(Class<?> clazz) {
    return injector.getInstance(clazz);
  }
}
