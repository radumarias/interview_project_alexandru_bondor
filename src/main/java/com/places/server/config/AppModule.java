package com.places.server.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.places.server.mapper.CityDTOMapper;
import com.places.server.mapper.PlaceDTOMapper;
import com.places.server.repository.PlaceRepository;
import com.places.server.repository.impl.PlaceRepositoryImpl;
import com.places.server.service.PlaceService;
import com.places.server.service.impl.PlaceServiceImpl;
import com.places.shared.service.PlaceRemoteService;
import java.io.IOException;
import java.util.Properties;

public class AppModule extends AbstractModule {

  @Override
  protected void configure() {
    bindProperties();

    install(new JpaPersistModule("places"));
    bind(PlaceRepository.class).to(PlaceRepositoryImpl.class).in(Singleton.class);
    bind(PlaceService.class).to(PlaceServiceImpl.class).in(Singleton.class);
    bind(PlaceRemoteService.class).to(PlaceServiceImpl.class).in(Singleton.class);
    bind(CityDTOMapper.class).in(Singleton.class);
    bind(PlaceDTOMapper.class).in(Singleton.class);
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
