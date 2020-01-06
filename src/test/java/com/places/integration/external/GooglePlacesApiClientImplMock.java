package com.places.integration.external;

import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import com.places.server.external.GooglePlacesApiClient;
import java.util.List;

public class GooglePlacesApiClientImplMock implements GooglePlacesApiClient {

  @Override
  public AutocompletePrediction[] getCities(String query) {
    return new AutocompletePrediction[0];
  }

  @Override
  public PlacesSearchResult[] getPlacesAround(String placeId, PlaceType placeType) {
    return new PlacesSearchResult[0];
  }

  @Override
  public PlaceDetails getPlaceDetails(String placeId) {
    return new PlaceDetails();
  }

  @Override
  public List<String> getPhotos(List<String> photoReference, int maxHeight) {
    return null;
  }
}
