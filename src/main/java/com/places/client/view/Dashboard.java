package com.places.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.places.client.components.CustomSuggestOracle;

public class Dashboard extends Composite {

  interface MyUiBinder extends UiBinder<Widget, Dashboard> {

  }

  private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  SuggestBox suggestBox;

  public Dashboard() {
    suggestBox = new SuggestBox(new CustomSuggestOracle());
    suggestBox.setLimit(10);
    initWidget(uiBinder.createAndBindUi(this));
  }
}
