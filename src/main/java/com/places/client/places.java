package com.places.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.places.client.view.DashboardView;

public class places implements EntryPoint {

  public void onModuleLoad() {

    final DashboardView dashboardView = new DashboardView();
    RootPanel.get().add(dashboardView);
  }
}
