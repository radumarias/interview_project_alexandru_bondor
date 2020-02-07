package com.places.client.components;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle.MultiWordSuggestion;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.places.shared.service.PlaceRemoteServiceAsync;
import com.places.shared.dto.CityDTO;
import java.util.ArrayList;
import java.util.List;

public class CitySuggestOracle extends SuggestOracle {

  private PlaceRemoteServiceAsync placeServiceAsync;

  private static final int REQUEST_CITIES_DELAY_MS = 500;

  private Request request;
  private Callback callback;

  private Timer timer = new Timer() {
    public void run() {
      updateSuggestionList(request, callback);
    }
  };

  private List<CityDTO> latestSuggestions = new ArrayList<>();

  public void setPlaceServiceAsync(PlaceRemoteServiceAsync placeServiceAsync) {
    this.placeServiceAsync = placeServiceAsync;
  }

  @Override
  public void requestSuggestions(Request request, Callback callback) {
    resetTimerCountdown(request, callback);
  }

  public List<CityDTO> getLatestSuggestions() {
    return latestSuggestions;
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
        if (cities == null || cities.isEmpty()) {
          Window.alert("No results from Google Places API");
        } else {
          latestSuggestions = cities;
          for (CityDTO cityDTO : cities) {
            final MultiWordSuggestion multiWordSuggestion = new MultiWordSuggestion(cityDTO.getName(), cityDTO.getName());
            suggestions.add(multiWordSuggestion);
          }
          Response response = new Response(suggestions);
          callback.onSuggestionsReady(request, response);
        }
      }
    });
  }
}
