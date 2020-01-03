package com.places.client.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle.MultiWordSuggestion;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.places.client.ServiceFactory;
import com.places.shared.PlaceServiceRequest;
import java.util.ArrayList;
import java.util.List;

public class CustomSuggestOracle extends SuggestOracle {

  @Override
  public void requestSuggestions(Request request, Callback callback) {
    GWT.log(request.getQuery());

    List<Suggestion> suggestions = new ArrayList<>();
    final PlaceServiceRequest placeServiceRequest = ServiceFactory.getInstance().getRequestFactory().getPlaceServiceRequest();

    placeServiceRequest.getString().fire(new Receiver<String>() {

      @Override
      public void onSuccess(String response) {
        final MultiWordSuggestion multiWordSuggestion = new MultiWordSuggestion(response, response);
        suggestions.add(multiWordSuggestion);
      }
    });

//    placeServiceRequest.getCities(request.getQuery()).fire(new Receiver<List<CityDTO>>() {
//      @Override
//      public void onSuccess(final List<CityDTO> cities) {
//        for (CityDTO cityDTO : cities) {
//          final MultiWordSuggestion multiWordSuggestion = new MultiWordSuggestion(cityDTO.getName(), cityDTO.getName());
//          suggestions.add(multiWordSuggestion);
//        }
//      }
//    });

    Response response = new Response(suggestions);
    response.setMoreSuggestionsCount(10);

    callback.onSuggestionsReady(request, response);
  }
}
