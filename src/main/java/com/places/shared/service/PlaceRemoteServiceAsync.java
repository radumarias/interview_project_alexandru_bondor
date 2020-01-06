package com.places.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.places.shared.dto.CityDTO;
import com.places.shared.dto.PlaceDTO;
import com.places.shared.enums.PlaceType;
import java.util.List;

public interface PlaceRemoteServiceAsync {

  void getCities(final String query, AsyncCallback<List<CityDTO>> async);

  void getPlacesAround(final String placeId, final PlaceType placeType, AsyncCallback<List<PlaceDTO>> async);

  void getPlaceDetails(final String placeId, AsyncCallback<PlaceDTO> async);

  void save(final PlaceDTO placeDTO, AsyncCallback<PlaceDTO> async);

  void delete(final PlaceDTO placeDTO, AsyncCallback<Boolean> async);

  void getPhotos(final List<String> photoReferences, final int maxHeight, AsyncCallback<List<String>> async);
}
