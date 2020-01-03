package com.places.server.config;

import com.google.inject.persist.PersistService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class AppPersistenceInitializer {

  @Inject
  AppPersistenceInitializer(final PersistService persistService) {
    persistService.start();
  }
}
