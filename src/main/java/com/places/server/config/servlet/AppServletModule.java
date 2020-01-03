package com.places.server.config.servlet;

import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

public class AppServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    bind(ExceptionHandler.class).to(AppExceptionHandler.class);
    bind(ServiceLayerDecorator.class).to(AppServiceLayerDecorator.class);
    serve("/").with(AppRequestFactoryServlet.class);
  }
}
