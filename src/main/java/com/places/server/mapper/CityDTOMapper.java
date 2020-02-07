package com.places.server.mapper;

import com.google.maps.model.AutocompletePrediction;
import com.places.shared.dto.CityDTO;
import java.util.ArrayList;
import java.util.List;

public class CityDTOMapper {

  public List<CityDTO> toDTOList(final AutocompletePrediction[] autocompletePredictions) {
    final List<CityDTO> cities = new ArrayList<>();
    if (autocompletePredictions != null && autocompletePredictions.length != 0) {
      for (AutocompletePrediction autocompletePrediction : autocompletePredictions) {
        cities.add(toDTO(autocompletePrediction));
      }
    }
    return cities;
  }

  public CityDTO toDTO(final AutocompletePrediction autocompletePrediction) {
    final CityDTO cityDTO = new CityDTO();
    cityDTO.setName(autocompletePrediction.structuredFormatting.mainText);
    cityDTO.setGooglePlaceId(autocompletePrediction.placeId);
    return cityDTO;
  }
}
