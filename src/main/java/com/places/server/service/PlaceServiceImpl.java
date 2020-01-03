package com.places.server.service;

import com.google.maps.model.AutocompletePrediction;
import com.places.client.service.PlaceService;
import com.places.server.dto.CityDTO;
import com.places.server.entity.Place;
import com.places.server.external.GooglePlacesApiClient;
import com.places.server.repository.AppRepository;
import com.places.server.repository.PlaceRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class PlaceServiceImpl extends AbstractServiceImpl<Place> implements PlaceService {

  @Inject
  private PlaceRepository placeRepository;

  @Inject
  private GooglePlacesApiClient googlePlacesApiClient;

  @Override
  public String getString() {
    return "hello";
  }

  //  /@Override
  public List<CityDTO> getCities(final String query) {
    final AutocompletePrediction[] autocompletePredictions = googlePlacesApiClient.getCities(query);

    return transformAutocompletePredictions(autocompletePredictions);
  }

  private List<CityDTO> transformAutocompletePredictions(final AutocompletePrediction[] autocompletePredictions) {
    final List<CityDTO> cities = new ArrayList<>();
    if (autocompletePredictions != null && autocompletePredictions.length != 0) {
      for (AutocompletePrediction autocompletePrediction : autocompletePredictions) {
        cities.add(transformAutocompletePrediction(autocompletePrediction));
      }
    }
    return cities;
  }

  private CityDTO transformAutocompletePrediction(final AutocompletePrediction autocompletePrediction) {
    final CityDTO cityDTO = new CityDTO();
    cityDTO.setName(autocompletePrediction.structuredFormatting.mainText);
    return cityDTO;
  }

  @Override
  public AppRepository<Place> getRepository() {
    return placeRepository;
  }
}
