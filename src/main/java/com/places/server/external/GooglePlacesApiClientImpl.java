package com.places.server.external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gwt.user.server.Base64Utils;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.maps.GeoApiContext;
import com.google.maps.ImageResult;
import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceAutocompleteType;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePlacesApiClientImpl implements GooglePlacesApiClient {

  private final Logger LOGGER = LoggerFactory.getLogger(GooglePlacesApiClientImpl.class);

  private final Integer PLACE_SEARCH_RADIUS_M = 5000;

  private final GeoApiContext geoApiContext;
  private SessionToken sessionToken;
  private Gson gson;

  @Inject
  public GooglePlacesApiClientImpl(@Named("google.api.key") final String googleApiKey) {
    geoApiContext = new GeoApiContext.Builder().apiKey(googleApiKey).retryTimeout(1, TimeUnit.SECONDS).build();
    sessionToken = new SessionToken();
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @Override
  public AutocompletePrediction[] getCities(final String query) {
    try {
      if (query == null || query.isEmpty() || query.length() < 3) {
        return null;
      }
      AutocompletePrediction[] autocompletePredictions = PlacesApi.placeAutocomplete(geoApiContext, query, sessionToken)
          .types(PlaceAutocompleteType.CITIES)
          .await();

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

  @Override
  public PlacesSearchResult[] getPlacesAround(final String placeId, final PlaceType placeType) {
    try {
      final PlaceDetails placeDetails = PlacesApi.placeDetails(geoApiContext, placeId).await();
      System.out.println(gson.toJson(placeDetails));
      final LatLng placeLocation = placeDetails.geometry.location;
      final PlacesSearchResponse placesSearchResponse = PlacesApi.nearbySearchQuery(geoApiContext, placeLocation).radius(PLACE_SEARCH_RADIUS_M).type(placeType)
          .await();
      for (PlacesSearchResult placesSearchResult : placesSearchResponse.results) {
        System.out.println(gson.toJson(placesSearchResult));
      }
      return placesSearchResponse.results;
    } catch (Exception exception) {
      LOGGER.warn(exception.getMessage());
    }
    return null;
  }

  @Override
  public PlaceDetails getPlaceDetails(final String placeId) {
    try {
      final PlaceDetails placeDetails = PlacesApi.placeDetails(geoApiContext, placeId).await();
      System.out.println(gson.toJson(placeDetails));
      return placeDetails;
    } catch (Exception exception) {
      LOGGER.warn(exception.getMessage());
    }
    return null;
  }

  @Override
  public List<String> getPhotos(final List<String> photoReferences, final int maxHeight) {
    try {
      List<String> photos = new ArrayList<>();
      for (String photoReference : photoReferences) {
        ImageResult imageResult = PlacesApi.photo(geoApiContext, photoReference).maxHeight(maxHeight).await();
        if (imageResult != null && imageResult.contentType != null && imageResult.contentType.length() != 0) {
          photos.add("data:image/png;base64," + Base64Utils.toBase64(imageResult.imageData));
        }
      }
      return photos;
    } catch (Exception exception) {
      LOGGER.warn(exception.getMessage());
    }
    return null;
  }
}
