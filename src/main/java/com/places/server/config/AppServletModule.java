package com.places.server.config;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.places.server.service.impl.PlaceServiceImpl;

public class AppServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    filter("/*").through(PersistFilter.class);
    serve("/api/places").with(PlaceServiceImpl.class);
  }
}
