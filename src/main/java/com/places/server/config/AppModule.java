package com.places.server.config;

import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.places.client.service.PlaceService;
import com.places.server.repository.PlaceRepository;
import com.places.server.repository.impl.PlaceRepositoryImpl;
import com.places.server.service.PlaceServiceImpl;

public class AppModule extends PrivateModule {

  @Override
  protected void configure() {
    install(new JpaPersistModule("places"));
    bind(PlaceRepository.class).to(PlaceRepositoryImpl.class).in(Singleton.class);
    expose(PlaceRepository.class);
    bind(PlaceService.class).to(PlaceServiceImpl.class).in(Singleton.class);
    expose(PlaceService.class);
  }
}
