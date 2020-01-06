package com.places.server.config;

import com.google.inject.PrivateModule;
import com.places.server.external.GooglePlacesApiClient;
import com.places.server.external.GooglePlacesApiClientImpl;

public class AppExternalClientsModule extends PrivateModule {

  @Override
  protected void configure() {
    bind(GooglePlacesApiClient.class).to(GooglePlacesApiClientImpl.class);
    expose(GooglePlacesApiClient.class);
  }
}
