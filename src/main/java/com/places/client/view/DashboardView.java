package com.places.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.places.client.components.CitySuggestOracle;
import com.places.shared.dto.CityDTO;
import com.places.shared.dto.PlaceDTO;
import com.places.shared.enums.PlaceType;
import com.places.shared.service.PlaceRemoteService;
import com.places.shared.service.PlaceRemoteServiceAsync;
import java.util.List;

public class DashboardView extends Composite {

  interface MyUiBinder extends UiBinder<Widget, DashboardView> {

  }

  private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField(provided = true)
  ListBox placeTypeSelect;

  @UiField(provided = true)
  SuggestBox citySelect;

  @UiField
  HTMLPanel placesTableContainer;

  @UiField
  HTMLPanel placeDetailsContainer;

  private PlacesTableView placesTableView = new PlacesTableView();
  private PlaceDetailsView placeDetailsView = new PlaceDetailsView();

  private PlaceRemoteServiceAsync placeServiceAsync = GWT.create(PlaceRemoteService.class);

  public DashboardView() {
    initPlacesTypeSelect();
    initCitySelect();
    initWidget(uiBinder.createAndBindUi(this));
    initPlacesTableView();
    initPlaceDetailsView();
  }

  private void initPlacesTypeSelect() {
    placeTypeSelect = new ListBox();
    PlaceType[] placeTypes = PlaceType.values();
    int defaultSelectedIndex = 0;
    for (int i = 0; i < placeTypes.length; i++) {
      final PlaceType placeType = placeTypes[i];
      if (PlaceType.TOURIST_ATTRACTION.equals(placeType)) {
        defaultSelectedIndex = i;
      }
      final String rawItem = placeType.toString();
      placeTypeSelect.addItem(rawItem);
    }
    placeTypeSelect.setSelectedIndex(defaultSelectedIndex);
  }

  private void initCitySelect() {

    final CitySuggestOracle citySuggestOracle = new CitySuggestOracle();
    citySuggestOracle.setPlaceServiceAsync(placeServiceAsync);

    citySelect = new SuggestBox(citySuggestOracle);
    final SelectionHandler<Suggestion> suggestionSelectionHandler = event -> {
      final String selectedCityName = event.getSelectedItem().getDisplayString();
      if (selectedCityName != null && !selectedCityName.isEmpty()) {
        final List<CityDTO> latestSuggestions = ((CitySuggestOracle) citySelect.getSuggestOracle()).getLatestSuggestions();
        final CityDTO selectedCityDTO = latestSuggestions.stream().filter(cityDTO -> selectedCityName.equals(cityDTO.getName())).findFirst().orElse(null);
        updatePlacesList(selectedCityDTO);
      }
    };
    citySelect.addSelectionHandler(suggestionSelectionHandler);
  }

  private void updatePlacesList(final CityDTO cityDTO) {
    if (cityDTO != null) {
      final String selectedValue = placeTypeSelect.getSelectedValue();
      final PlaceType placeType = PlaceType.valueOf(selectedValue.toUpperCase());
      placeServiceAsync.getPlacesAround(cityDTO.getGooglePlaceId(), placeType, new AsyncCallback<List<PlaceDTO>>() {
        @Override
        public void onFailure(Throwable caught) {
          Window.alert("Something went wrong during places update.");
          caught.printStackTrace();
        }

        @Override
        public void onSuccess(List<PlaceDTO> result) {
          if (result == null || result.isEmpty()) {
            Window.alert("No results from Google Places API");
          } else {
            placesTableView.updateData(result);
          }
        }
      });
    }
  }

  private void initPlacesTableView() {
    placesTableContainer.add(placesTableView);
    placesTableView.addSelectionChangeHandler(event -> {
      final PlaceDTO selectedPlaceDTO = placesTableView.getSelected();
      updatePlaceDetails(selectedPlaceDTO);
    });
  }

  private void updatePlaceDetails(final PlaceDTO placeDTO) {
    if (placeDTO != null) {
      placeServiceAsync.getPlaceDetails(placeDTO.getGooglePlaceId(), new AsyncCallback<PlaceDTO>() {
        @Override
        public void onFailure(Throwable caught) {
          Window.alert("Something went wrong during place details update.");
          caught.printStackTrace();
        }

        @Override
        public void onSuccess(final PlaceDTO result) {
          placeDetailsView.updatePlaceDetails(result);
          updatePlaceDetailsPhotos(result);
        }
      });
    }
  }

  private void updatePlaceDetailsPhotos(final PlaceDTO result) {
    List<String> photoReferences = result.getPhotoReferences();
    if (photoReferences != null && !photoReferences.isEmpty()) {
      placeServiceAsync.getPhotos(photoReferences, 150, new AsyncCallback<List<String>>() {

        @Override
        public void onFailure(Throwable caught) {
          Window.alert("Something went wrong during fetching place photos from Google.");
          caught.printStackTrace();
        }

        @Override
        public void onSuccess(List<String> result) {
          GWT.log(String.join("\n", result));
          placeDetailsView.updatePhotos(result);
        }
      });
    }
  }

  private void initPlaceDetailsView() {
    placeDetailsContainer.add(placeDetailsView);

    placeDetailsView.setSaveButtonHandler(event -> {
      final PlaceDTO placeDTO = placeDetailsView.getPlaceDetails();
      save(placeDTO);
    });

    placeDetailsView.setDeleteButtonHandler(event -> {
      final PlaceDTO placeDTO = placeDetailsView.getPlaceDetails();
      delete(placeDTO);
    });
  }

  private void save(final PlaceDTO placeDTO) {
    placeServiceAsync.save(placeDTO, new AsyncCallback<PlaceDTO>() {
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Something went wrong during saving place details.");
        caught.printStackTrace();
      }

      @Override
      public void onSuccess(PlaceDTO result) {
        if (result.getId() != null) {
          Window.alert("Successfully saved place details.");
          final List<PlaceDTO> data = placesTableView.getData();
          int index = data.indexOf(result);
          data.set(index, placeDTO);
          placesTableView.updateData(data);
          placeDetailsView.updatePlaceDetails(result);
        }
      }
    });
  }

  private void delete(final PlaceDTO placeDTO) {
    placeServiceAsync.delete(placeDTO, new AsyncCallback<Boolean>() {
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Something went wrong during deleting place details.");
        caught.printStackTrace();
      }

      @Override
      public void onSuccess(Boolean result) {
        if (result) {
          Window.alert("Successfully deleted place details.");
          final List<PlaceDTO> data = placesTableView.getData();
          data.remove(placeDTO);
          placesTableView.updateData(data);
        } else {
          Window.alert("Deleting place details failed");
        }
      }
    });
  }
}
