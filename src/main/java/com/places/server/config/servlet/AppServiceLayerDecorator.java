package com.places.server.config.servlet;

import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import javax.inject.Inject;

public class AppServiceLayerDecorator extends ServiceLayerDecorator {

  private Injector injector;

  @Inject
  public AppServiceLayerDecorator(final Injector injector) {
    this.injector = injector;
  }

  @Override
  public <T extends Locator<?, ?>> T createLocator(Class<T> clazz) {
    return injector.getInstance(clazz);
  }

  @Override
  public Object createServiceInstance(Class<? extends RequestContext> requestContext) {
    final Class<? extends ServiceLocator> serviceLocator = getTop().resolveServiceLocator(requestContext);

    if (serviceLocator != null) {
      final ServiceLocator serviceLocatorImpl = injector.getInstance(serviceLocator);
      final Class<?> serviceClass = requestContext.getAnnotation(Service.class).value();
      return serviceLocatorImpl.getInstance(serviceClass);
    }
    return null;
  }
}
