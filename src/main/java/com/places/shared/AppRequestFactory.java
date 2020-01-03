package com.places.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface AppRequestFactory extends RequestFactory {

  PlaceServiceRequest getPlaceServiceRequest();
}
