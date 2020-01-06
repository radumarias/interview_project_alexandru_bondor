package com.places.server.repository;


import com.places.server.entity.Place;
import java.util.List;

public interface PlaceRepository extends AppRepository<Place> {

  Place findByGooglePlaceId(final String googlePlaceId);

  List<Place> findByGooglePlaceIdIn(final List<String> googlePlaceIds);

  Place findByGooglePlaceIdAndUpdated(final String googlePlaceId, final boolean updated);
}
