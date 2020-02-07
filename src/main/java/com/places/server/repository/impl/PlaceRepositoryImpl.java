package com.places.server.repository.impl;

import com.places.server.entity.Place;
import com.places.server.repository.PlaceRepository;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PlaceRepositoryImpl extends AbstractRepositoryImpl<Place> implements PlaceRepository {

  @Override
  public Place findByGooglePlaceId(final String googlePlaceId) {
    final List<Place> places = findByGooglePlaceIdIn(Collections.singletonList(googlePlaceId));
    if (places != null && !places.isEmpty()) {
      return places.get(0);
    }
    return null;
  }

  @Override
  public List<Place> findByGooglePlaceIdIn(List<String> googlePlaceIds) {
    final EntityManager entityManager = entityManagerProvider.get();
    final CriteriaQuery<Place> createQuery = entityManager.getCriteriaBuilder().createQuery(getClassType());
    final Root<Place> root = createQuery.from(getClassType());
    createQuery.where(root.get("googlePlaceId").in(googlePlaceIds));
    return entityManager.createQuery(createQuery).getResultList();
  }

  @Override
  public Place findByGooglePlaceIdAndUpdated(String googlePlaceId, final boolean updated) {
    final EntityManager entityManager = entityManagerProvider.get();
    final CriteriaQuery<Place> createQuery = entityManager.getCriteriaBuilder().createQuery(getClassType());
    final Root<Place> root = createQuery.from(getClassType());
    createQuery.where(
        root.get("googlePlaceId").in(googlePlaceId),
        root.get("updated").in(updated));
    final List<Place> result = entityManager.createQuery(createQuery).getResultList();
    if (result != null && result.size() != 0) {
      return result.get(0);
    }
    return null;
  }

  @Override
  final Class<Place> getClassType() {
    return Place.class;
  }
}
