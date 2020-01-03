package com.places.server.config.servlet;

import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppRequestFactoryServlet extends RequestFactoryServlet {

  @Inject
  public AppRequestFactoryServlet(
      final ExceptionHandler exceptionHandler,
      final ServiceLayerDecorator serviceLayerDecorator) {
    super(exceptionHandler, serviceLayerDecorator);
  }

}
