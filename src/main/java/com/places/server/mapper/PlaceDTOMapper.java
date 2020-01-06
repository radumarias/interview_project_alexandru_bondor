package com.places.server.mapper;

import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.places.server.entity.Place;
import com.places.shared.dto.PlaceDTO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceDTOMapper {

  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public List<PlaceDTO> toDTOList(final PlacesSearchResult[] placesSearchResults) {
    final List<PlaceDTO> places = new ArrayList<>();
    if (placesSearchResults != null && placesSearchResults.length != 0) {
      for (PlacesSearchResult placesSearchResult : placesSearchResults) {
        places.add(toDTO(placesSearchResult));
      }
    }
    return places;
  }

  public List<PlaceDTO> toDTOList(final List<Place> places) {
    final List<PlaceDTO> placeDTOs = new ArrayList<>();
    if (places != null && places.size() != 0) {
      for (Place place : places) {
        placeDTOs.add(toDTO(place));
      }
    }
    return placeDTOs;
  }

  public PlaceDTO toDTO(final PlacesSearchResult placesSearchResult) {
    return toBaseDTO(
        placesSearchResult.name,
        placesSearchResult.placeId,
        placesSearchResult.rating,
        placesSearchResult.userRatingsTotal,
        placesSearchResult.vicinity
    );
  }

  public PlaceDTO toDTO(final PlaceDetails placeDetails) {
    final PlaceDTO placeDTO = toBaseDTO(
        placeDetails.name,
        placeDetails.placeId,
        placeDetails.rating,
        placeDetails.userRatingsTotal,
        placeDetails.formattedAddress
    );
    placeDTO.setPhoneNumber(placeDetails.internationalPhoneNumber);
    if (placeDetails.website != null) {
      placeDTO.setWebsite(placeDetails.website.toString());
    }
    placeDTO.setLat(placeDetails.geometry.location.lat);
    placeDTO.setLng(placeDetails.geometry.location.lng);
    if (placeDetails.photos != null) {
      final List<String> photoReferences = Stream.of(placeDetails.photos).filter(Objects::nonNull).map(photo -> photo.photoReference)
          .collect(Collectors.toList());
      placeDTO.setPhotoReferences(photoReferences);
    }
    return placeDTO;
  }

  public PlaceDTO toDTO(final Place place) {
    final PlaceDTO placeDTO = toBaseDTO(
        place.getName(),
        place.getGooglePlaceId(),
        place.getRating(),
        place.getRatingsCount(),
        place.getAddress()
    );
    placeDTO.setId(place.getId());
    placeDTO.setPhoneNumber(place.getPhoneNumber());
    placeDTO.setWebsite(place.getWebsite());
    placeDTO.setLat(place.getLat());
    placeDTO.setLng(place.getLng());
    placeDTO.setPhotoReferences(new ArrayList<>(place.getPhotoReferences()));
    placeDTO.setUpdated(place.isUpdated());
    return placeDTO;
  }

  private PlaceDTO toBaseDTO(
      final String name,
      final String placeId,
      final double rating,
      final int userRatingsTotal,
      final String address
  ) {
    final PlaceDTO placeDTO = new PlaceDTO();
    placeDTO.setName(name);
    placeDTO.setGooglePlaceId(placeId);
    final Double roundedRating = Double.valueOf(decimalFormat.format(rating));
    placeDTO.setRating(roundedRating);
    placeDTO.setRatingsCount(userRatingsTotal);
    placeDTO.setAddress(address);
    return placeDTO;
  }

  public List<Place> toEntityList(final List<PlaceDTO> placeDTOs) {
    final List<Place> places = new ArrayList<>();
    if (placeDTOs != null && !placeDTOs.isEmpty()) {
      placeDTOs.forEach(placeDTO -> places.add(toEntity(placeDTO)));
    }
    return places;
  }

  public Place toEntity(final PlaceDTO placeDTO) {
    final Place place = new Place();
    place.setId(placeDTO.getId());
    place.setGooglePlaceId(placeDTO.getGooglePlaceId());
    place.setName(placeDTO.getName());
    place.setAddress(placeDTO.getAddress());
    place.setPhoneNumber(placeDTO.getPhoneNumber());
    place.setRating(placeDTO.getRating());
    place.setRatingsCount(placeDTO.getRatingsCount());
    place.setLat(placeDTO.getLat());
    place.setLng(placeDTO.getLng());
    place.setWebsite(placeDTO.getWebsite());
    place.setPhotoReferences(new ArrayList<>(placeDTO.getPhotoReferences()));
    place.setUpdated(placeDTO.isUpdated());
    return place;
  }
}
