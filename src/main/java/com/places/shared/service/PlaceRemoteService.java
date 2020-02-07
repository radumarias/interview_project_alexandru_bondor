package com.places.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.places.shared.dto.CityDTO;
import com.places.shared.dto.PlaceDTO;
import com.places.shared.enums.PlaceType;
import java.util.List;

@RemoteServiceRelativePath("places")
public interface PlaceRemoteService extends RemoteService {

  List<CityDTO> getCities(final String query);

  List<PlaceDTO> getPlacesAround(final String placeId, final PlaceType placeType);

  PlaceDTO getPlaceDetails(final String placeId);

  PlaceDTO save(final PlaceDTO placeDTO);

  boolean delete(final PlaceDTO placeDTO);

  List<String> getPhotos(final List<String> photoReferences, final int maxHeight);
}
