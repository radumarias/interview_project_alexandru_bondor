package com.places.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.places.client.dto.CityDTO;
import java.util.List;

@RemoteServiceRelativePath("places")
public interface PlaceService extends RemoteService {

  List<CityDTO> getCities(final String query);
}
