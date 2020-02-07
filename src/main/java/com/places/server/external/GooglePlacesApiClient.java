package com.places.server.external;

import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import java.util.List;

public interface GooglePlacesApiClient {

  AutocompletePrediction[] getCities(final String query);

  PlacesSearchResult[] getPlacesAround(final String placeId, final PlaceType placeType);

  PlaceDetails getPlaceDetails(final String placeId);

  List<String> getPhotos(final List<String> photoReference, final int maxHeight);
}
