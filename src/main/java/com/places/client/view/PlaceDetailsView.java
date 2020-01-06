package com.places.client.view;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.places.shared.dto.PlaceDTO;
import java.util.List;

public class PlaceDetailsView extends Composite {

  interface MyUiBinder extends UiBinder<Widget, PlaceDetailsView> {

  }

  private static PlaceDetailsView.MyUiBinder uiBinder = GWT.create(PlaceDetailsView.MyUiBinder.class);

  @UiField
  TextBox placeName;
  @UiField
  TextBox placeAddress;
  @UiField
  TextBox placePhoneNumber;
  @UiField
  TextBox placeRating;
  @UiField
  TextBox placeRatingsCount;
  @UiField
  TextBox placeWebsite;

  @UiField
  ScrollPanel photos;

  @UiField
  Button saveButton;
  @UiField
  Button deleteButton;

  private PlaceDTO currentPlaceDTO;

  public PlaceDetailsView() {
    initWidget(uiBinder.createAndBindUi(this));
    placeRating.setReadOnly(true);
    placeRatingsCount.setReadOnly(true);
    photos.setWidth("300px");
    photos.setHeight("150px");
    photos.setAlwaysShowScrollBars(true);
    photos.setVisible(true);
  }

  public void updatePlaceDetails(final PlaceDTO placeDTO) {
    if (placeDTO != null) {
      this.currentPlaceDTO = placeDTO;
      placeName.setText(placeDTO.getName());
      placeAddress.setText(placeDTO.getAddress());
      placePhoneNumber.setText(placeDTO.getPhoneNumber());
      placeRating.setText(String.valueOf(placeDTO.getRating()));
      placeRatingsCount.setText(String.valueOf(placeDTO.getRatingsCount()));
      placeWebsite.setText(placeDTO.getWebsite());

      GWT.log(String.valueOf(placeDTO.isUpdated()));
      deleteButton.setEnabled(placeDTO.isUpdated());
    }
  }

  public void updatePhotos(final List<String> contents) {
    if (contents != null && !contents.isEmpty()) {
      HorizontalPanel horizontalPanel = new HorizontalPanel();
      for (String content : contents) {
        Canvas canvas = Canvas.createIfSupported();
        canvas.setHeight("140px");
        ImageElement imageElement = Document.get().createImageElement();
        imageElement.setSrc(content);
        Context2d context2d = canvas.getContext2d();
        GWT.log(imageElement.getSrc());
        context2d.drawImage(imageElement, 0.0, 0.0);
        horizontalPanel.add(canvas);
        photos.add(horizontalPanel);
      }
    }
  }

  public void setSaveButtonHandler(final ClickHandler clickHandler) {
    saveButton.addClickHandler(clickHandler);
  }

  public void setDeleteButtonHandler(final ClickHandler clickHandler) {
    deleteButton.addClickHandler(clickHandler);
  }

  public PlaceDTO getPlaceDetails() {
    final PlaceDTO placeDTO = new PlaceDTO();
    placeDTO.setId(currentPlaceDTO.getId());
    placeDTO.setGooglePlaceId(currentPlaceDTO.getGooglePlaceId());
    placeDTO.setPhotoReferences(currentPlaceDTO.getPhotoReferences());
    placeDTO.setLat(currentPlaceDTO.getLat());
    placeDTO.setLng(currentPlaceDTO.getLng());

    placeDTO.setName(placeName.getText());
    placeDTO.setAddress(placeAddress.getText());
    placeDTO.setPhoneNumber(placePhoneNumber.getText());
    placeDTO.setRating(Double.valueOf(placeRating.getText()));
    placeDTO.setRatingsCount(Integer.valueOf(placeRatingsCount.getText()));
    placeDTO.setWebsite(placeWebsite.getText());
    return placeDTO;
  }
}
