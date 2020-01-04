package com.places.integration.config;

import com.google.inject.persist.PersistService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class AppTestPersistenceInitializer {

  @Inject
  AppTestPersistenceInitializer(final PersistService persistService) {
    persistService.start();
  }
}
