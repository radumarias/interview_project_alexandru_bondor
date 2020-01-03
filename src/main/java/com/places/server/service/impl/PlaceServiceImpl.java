package com.places.server.service.impl;

import com.places.server.entity.Place;
import com.places.server.repository.AppRepository;
import com.places.server.repository.PlaceRepository;
import com.places.server.service.PlaceService;
import javax.inject.Inject;

public class PlaceServiceImpl extends AbstractServiceImpl<Place> implements PlaceService {

  private PlaceRepository placeRepository;

  @Inject
  public PlaceServiceImpl(final PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
  }

  @Override
  public AppRepository<Place> getRepository() {
    return placeRepository;
  }
}
