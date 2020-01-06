package com.places.shared.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceDTO extends GooglePlacesApiDTO {

  private Long id;
  private String address;
  private String phoneNumber;
  private Double rating;
  private Integer ratingsCount;
  private String website;
  private Double lat;
  private Double lng;
  private List<String> photoReferences = new ArrayList<>();
  private boolean updated;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Integer getRatingsCount() {
    return ratingsCount;
  }

  public void setRatingsCount(Integer ratingsCount) {
    this.ratingsCount = ratingsCount;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public List<String> getPhotoReferences() {
    return photoReferences;
  }

  public void setPhotoReferences(List<String> photoReferences) {
    this.photoReferences = photoReferences;
  }

  public boolean isUpdated() {
    return updated;
  }

  public void setUpdated(boolean updated) {
    this.updated = updated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaceDTO placeDTO = (PlaceDTO) o;
    return Objects.equals(super.getGooglePlaceId(), placeDTO.getGooglePlaceId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.getGooglePlaceId());
  }
}
