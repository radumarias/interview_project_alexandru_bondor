package com.places.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("places")
public interface PlaceService extends RemoteService {

  //List<CityDTO> getCities(final String query);

  String getString();
}
