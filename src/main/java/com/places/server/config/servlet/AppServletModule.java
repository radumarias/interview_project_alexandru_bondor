package com.places.server.config.servlet;

import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.ExceptionHandler;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import java.util.HashMap;
import java.util.Map;

public class AppServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    bind(ExceptionHandler.class).to(AppExceptionHandler.class);
    bind(ServiceLayerDecorator.class).to(AppServiceLayerDecorator.class);

    Map<String, String> params = new HashMap<>();
    params.put("symbolMapsDirectory", "WEB-INF/classes/symbolMaps/");
    serve("/gwtRequest").with(AppRequestFactoryServlet.class, params);
  }
}
