package com.places.shared.dto;

import java.io.Serializable;

public abstract class GooglePlacesApiDTO implements Serializable {

  private String googlePlaceId;

  private String name;

  public String getGooglePlaceId() {
    return googlePlaceId;
  }

  public void setGooglePlaceId(String googlePlaceId) {
    this.googlePlaceId = googlePlaceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
