package com.places.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.places.client.view.Dashboard;

public class places implements EntryPoint {

  public void onModuleLoad() {

    final Dashboard dashboard = new Dashboard();
    RootPanel.get().add(dashboard);
  }
}
