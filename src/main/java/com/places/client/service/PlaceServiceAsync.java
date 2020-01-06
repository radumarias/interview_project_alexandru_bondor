package com.places.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.places.client.dto.CityDTO;
import java.util.List;

public interface PlaceServiceAsync {

  void getCities(final String query, AsyncCallback<List<CityDTO>> async);
}
