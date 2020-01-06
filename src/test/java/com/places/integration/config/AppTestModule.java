package com.places.integration.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.places.client.service.PlaceService;
import com.places.integration.external.GooglePlacesApiClientImplMock;
import com.places.server.external.GooglePlacesApiClient;
import com.places.server.repository.PlaceRepository;
import com.places.server.repository.impl.PlaceRepositoryImpl;
import com.places.server.service.impl.PlaceServiceImpl;
import java.io.IOException;
import java.util.Properties;

public class AppTestModule extends AbstractModule {

  @Override
  protected void configure() {
    bindProperties();

    install(new JpaPersistModule("places"));
    bind(PlaceRepository.class).to(PlaceRepositoryImpl.class).in(Singleton.class);
    bind(PlaceService.class).to(PlaceServiceImpl.class).in(Singleton.class);
    bind(GooglePlacesApiClient.class).to(GooglePlacesApiClientImplMock.class);
    bind(AppTestPersistenceInitializer.class).asEagerSingleton();
  }

  private void bindProperties() {
    try {
      final Properties properties = new Properties();
      properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
      Names.bindProperties(binder(), properties);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
