package com.places.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle.MultiWordSuggestion;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.places.client.dto.CityDTO;
import com.places.client.service.PlaceService;
import com.places.client.service.PlaceServiceAsync;
import java.util.ArrayList;
import java.util.List;

public class CitySuggestOracle extends SuggestOracle {

  private PlaceServiceAsync placeServiceAsync = GWT.create(PlaceService.class);

  private static final int REQUEST_CITIES_DELAY_MS = 500;

  private Request request;
  private Callback callback;

  private Timer timer = new Timer() {
    public void run() {
      updateSuggestionList(request, callback);
    }
  };

  @Override
  public void requestSuggestions(Request request, Callback callback) {
    resetTimerCountdown(request, callback);
  }

  private void resetTimerCountdown(Request request, Callback callback) {
    this.request = request;
    this.callback = callback;
    timer.cancel();
    timer.schedule(REQUEST_CITIES_DELAY_MS);
  }

  private void updateSuggestionList(Request request, Callback callback) {
    final List<Suggestion> suggestions = new ArrayList<>();

    placeServiceAsync.getCities(request.getQuery(), new AsyncCallback<List<CityDTO>>() {
      @Override
      public void onFailure(Throwable caught) {
        caught.printStackTrace();
      }

      @Override
      public void onSuccess(List<CityDTO> cities) {
        for (CityDTO cityDTO : cities) {
          final MultiWordSuggestion multiWordSuggestion = new MultiWordSuggestion(cityDTO.getName(), cityDTO.getName());
          suggestions.add(multiWordSuggestion);
        }
        Response response = new Response(suggestions);
        callback.onSuggestionsReady(request, response);
      }
    });
  }
}
