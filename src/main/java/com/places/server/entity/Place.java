package com.places.server.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

@Entity
@Table(name = "place")
public class Place extends AbstractEntity {

  @Column
  private String name;
  @Column
  private String googlePlaceId;
  @Column
  private String address;
  @Column
  private String phoneNumber;
  @Column
  private Double rating;
  @Column
  private Integer ratingsCount;
  @Column
  private String website;
  @Column
  private Double lat;
  @Column
  private Double lng;
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> photoReferences;
  @Column
  private boolean updated;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGooglePlaceId() {
    return googlePlaceId;
  }

  public void setGooglePlaceId(String googlePlaceId) {
    this.googlePlaceId = googlePlaceId;
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
    Place place = (Place) o;
    return Objects.equals(googlePlaceId, place.googlePlaceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(googlePlaceId);
  }
}
