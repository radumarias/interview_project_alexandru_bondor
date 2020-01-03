package com.places.server.external;

import com.google.maps.model.AutocompletePrediction;

public interface GooglePlacesApiClient {

  AutocompletePrediction[] getCities(final String query);
}
