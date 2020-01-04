package com.places.integration.external;

import com.google.maps.model.AutocompletePrediction;
import com.places.server.external.GooglePlacesApiClient;

public class GooglePlacesApiClientImplMock implements GooglePlacesApiClient {

  @Override
  public AutocompletePrediction[] getCities(String query) {
    return new AutocompletePrediction[0];
  }
}
