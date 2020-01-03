package com.places.shared;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.places.client.service.PlaceService;

@Service(value = PlaceService.class)
public interface PlaceServiceRequest extends RequestContext {

  //Request<List<CityDTO>> getCities(final String query);
  Request<String> getString();
}
