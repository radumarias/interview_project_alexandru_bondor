package com.places.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.places.client.components.CitySuggestOracle;

public class Dashboard extends Composite {

  interface MyUiBinder extends UiBinder<Widget, Dashboard> {

  }

  private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  SuggestBox suggestBox;

  public Dashboard() {
    initSuggestBox();
    initWidget(uiBinder.createAndBindUi(this));
  }

  private void initSuggestBox() {
    suggestBox = new SuggestBox(new CitySuggestOracle());
    final SelectionHandler<Suggestion> suggestionSelectionHandler = event -> {
      final String selectedCityName = event.getSelectedItem().getDisplayString();
      updatePlacesList(selectedCityName);
    };
    suggestBox.addSelectionHandler(suggestionSelectionHandler);
  }

  private void updatePlacesList(final String selectedCityName) {
    GWT.log(selectedCityName);
  }
}
