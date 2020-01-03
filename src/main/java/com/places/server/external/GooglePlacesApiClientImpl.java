package com.places.server.external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceAutocompleteType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePlacesApiClientImpl implements GooglePlacesApiClient {

  private final Logger LOGGER = LoggerFactory.getLogger(GooglePlacesApiClientImpl.class);

  private final GeoApiContext geoApiContext;

  @Inject
  public GooglePlacesApiClientImpl(@Named("google.api.key") final String googleApiKey) {
    geoApiContext = new GeoApiContext.Builder().apiKey(googleApiKey).build();
  }

  @Override
  public AutocompletePrediction[] getCities(final String query) {
    try {
      AutocompletePrediction[] autocompletePredictions = PlacesApi.placeAutocomplete(geoApiContext, query, null).types(PlaceAutocompleteType.CITIES).await();
      Gson gson = new GsonBuilder().setPrettyPrinting().create();

      System.out.println("Found " + autocompletePredictions.length + " cities");
      for (AutocompletePrediction autocompletePrediction : autocompletePredictions) {
        System.out.println(gson.toJson(autocompletePrediction));
      }
      return autocompletePredictions;
    } catch (Exception exception) {
      LOGGER.warn(exception.getMessage());
    }
    return null;
  }
}
