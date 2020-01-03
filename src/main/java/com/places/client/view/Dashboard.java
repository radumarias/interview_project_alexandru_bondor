package com.places.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Dashboard extends Composite {

  interface MyUiBinder extends UiBinder<Widget, Dashboard> {

  }

  private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  public Dashboard() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}
