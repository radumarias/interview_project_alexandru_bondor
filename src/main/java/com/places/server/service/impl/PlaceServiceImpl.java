package com.places.server.service.impl;

import com.google.inject.Singleton;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.places.server.entity.Place;
import com.places.server.external.GooglePlacesApiClient;
import com.places.server.mapper.CityDTOMapper;
import com.places.server.mapper.PlaceDTOMapper;
import com.places.server.repository.AppRepository;
import com.places.server.repository.PlaceRepository;
import com.places.server.service.PlaceService;
import com.places.shared.dto.CityDTO;
import com.places.shared.dto.GooglePlacesApiDTO;
import com.places.shared.dto.PlaceDTO;
import com.places.shared.enums.PlaceType;
import com.places.shared.service.PlaceRemoteService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;

@Singleton
public class PlaceServiceImpl extends AbstractServiceImpl<Place> implements PlaceRemoteService, PlaceService {

  private PlaceRepository placeRepository;
  private GooglePlacesApiClient googlePlacesApiClient;
  private CityDTOMapper cityDTOMapper;
  private PlaceDTOMapper placeDTOMapper;

  @Inject
  public PlaceServiceImpl(
      final PlaceRepository placeRepository,
      final GooglePlacesApiClient googlePlacesApiClient,
      final CityDTOMapper cityDTOMapper,
      final PlaceDTOMapper placeDTOMapper) {
    this.placeRepository = placeRepository;
    this.googlePlacesApiClient = googlePlacesApiClient;
    this.cityDTOMapper = cityDTOMapper;
    this.placeDTOMapper = placeDTOMapper;
  }

  @Override
  public List<CityDTO> getCities(final String query) {
    final AutocompletePrediction[] autocompletePredictions = googlePlacesApiClient.getCities(query);
    return cityDTOMapper.toDTOList(autocompletePredictions);
  }

  @Override
  public List<PlaceDTO> getPlacesAround(final String placeId, final PlaceType placeType) {
    final String placeTypeString = placeType.toString();
    com.google.maps.model.PlaceType googlePlaceType = com.google.maps.model.PlaceType.valueOf(placeTypeString.toUpperCase());

    final PlacesSearchResult[] placesSearchResults = googlePlacesApiClient.getPlacesAround(placeId, googlePlaceType);
    final List<PlaceDTO> googlePlaces = placeDTOMapper.toDTOList(placesSearchResults);

    return saveNewGooglePlaces(googlePlaces);
  }

  private List<PlaceDTO> saveNewGooglePlaces(final List<PlaceDTO> googlePlaceDTOs) {
    if (googlePlaceDTOs != null && !googlePlaceDTOs.isEmpty()) {
      final List<String> googlePlaceIds = googlePlaceDTOs.stream().map(GooglePlacesApiDTO::getGooglePlaceId).collect(Collectors.toList());

      final Map<String, Place> existingPlacesByGooglePlaceId = placeRepository.findByGooglePlaceIdIn(googlePlaceIds).stream()
          .collect(Collectors.toMap(Place::getGooglePlaceId, Function.identity()));

      final List<Place> placesToSave = placeDTOMapper.toEntityList(googlePlaceDTOs);
      final List<Place> existingUpdatedPlaces = new ArrayList<>();
      final Iterator<Place> iterator = placesToSave.iterator();
      while (iterator.hasNext()) {
        final Place currentPlace = iterator.next();
        final Place existingPlace = existingPlacesByGooglePlaceId.get(currentPlace.getGooglePlaceId());
        if (existingPlace != null) {
          if (!existingPlace.isUpdated()) {
            currentPlace.setId(existingPlace.getId());
          } else {
            existingUpdatedPlaces.add(existingPlace);
            iterator.remove();
          }
        }
      }
      final List<Place> savedPlaces = placeRepository.save(placesToSave);
      savedPlaces.addAll(existingUpdatedPlaces);
      return placeDTOMapper.toDTOList(savedPlaces);
    }
    return new ArrayList<>();
  }

  @Override
  public PlaceDTO getPlaceDetails(final String placeId) {
    final Place savedPlace = placeRepository.findByGooglePlaceIdAndUpdated(placeId, true);
    if (savedPlace != null) {
      return placeDTOMapper.toDTO(savedPlace);
    } else {
      final PlaceDetails placeDetails = googlePlacesApiClient.getPlaceDetails(placeId);
      return placeDTOMapper.toDTO(placeDetails);
    }
  }

  @Override
  public PlaceDTO save(PlaceDTO placeDTO) {
    placeDTO.setUpdated(true);
    final Place existingPlace = placeRepository.findByGooglePlaceId(placeDTO.getGooglePlaceId());
    if (existingPlace != null) {
      placeDTO.setId(existingPlace.getId());
    }
    final Place savedEntity = super.save(placeDTOMapper.toEntity(placeDTO));
    return placeDTOMapper.toDTO(savedEntity);
  }

  @Override
  public boolean delete(PlaceDTO placeDTO) {
    final Place place = placeDTOMapper.toEntity(placeDTO);
    return placeRepository.delete(place);
  }

  @Override
  public List<String> getPhotos(final List<String> photoReferences, final int maxHeight) {
    return googlePlacesApiClient.getPhotos(photoReferences, maxHeight);
  }

  @Override
  public AppRepository<Place> getRepository() {
    return placeRepository;
  }
}
