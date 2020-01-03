package com.places.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.places.shared.AppRequestFactory;

public class ServiceFactory {

  private static ServiceFactory instance;

  private AppRequestFactory requestFactory = GWT.create(AppRequestFactory.class);

  private ServiceFactory() {
    GWT.log("here");
    final EventBus eventBus = new SimpleEventBus();
    requestFactory.initialize(eventBus);
  }

  public static ServiceFactory getInstance() {
    if (instance == null) {
      instance = new ServiceFactory();
    }
    return instance;
  }

  public AppRequestFactory getRequestFactory() {
    return requestFactory;
  }
}
