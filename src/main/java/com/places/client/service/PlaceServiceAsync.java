package com.places.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.places.server.entity.Place;

public interface PlaceServiceAsync {

  void getString(AsyncCallback<String> async);
}
