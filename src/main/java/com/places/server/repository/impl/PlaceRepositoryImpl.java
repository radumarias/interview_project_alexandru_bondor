package com.places.server.repository.impl;

import com.places.server.entity.Place;
import com.places.server.repository.PlaceRepository;

public class PlaceRepositoryImpl extends AbstractRepositoryImpl<Place> implements PlaceRepository {

  @Override
  final Class<Place> getClassType() {
    return Place.class;
  }
}
