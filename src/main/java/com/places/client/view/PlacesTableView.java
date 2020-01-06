package com.places.client.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.places.shared.dto.PlaceDTO;
import java.util.Comparator;
import java.util.List;

public class PlacesTableView extends Composite {

  interface MyUiBinder extends UiBinder<Widget, PlacesTableView> {

  }

  private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  @UiField
  CellTable<PlaceDTO> cellTable;

  @UiField
  SimplePager pager;

  private ListDataProvider<PlaceDTO> dataProvider = new ListDataProvider<>();
  private SingleSelectionModel<PlaceDTO> selectionModel;
  private ListHandler<PlaceDTO> sortHandler = new MyListHandler(dataProvider.getList());

  private class MyListHandler extends ListHandler<PlaceDTO> {

    MyListHandler(final List<PlaceDTO> list) {
      super(list);
    }

    @Override
    public void onColumnSort(ColumnSortEvent event) {
      super.onColumnSort(event);
      dataProvider.refresh();
      cellTable.redraw();
    }
  }

  public PlacesTableView() {
    initWidget(uiBinder.createAndBindUi(this));
    initTable();
  }

  private void initTable() {
    cellTable.setPageSize(10);
    cellTable.addColumnSortHandler(sortHandler);

    final Column<PlaceDTO, String> placeNameColumn = getPlaceNameColumn();
    placeNameColumn.setSortable(true);
    sortHandler.setComparator(placeNameColumn, Comparator.comparing(PlaceDTO::getName));
    cellTable.addColumn(placeNameColumn, "Name");

    cellTable.addColumn(getPlaceAddressColumn(), "Address");

    final Column<PlaceDTO, String> placeRatingColumn = getPlaceRatingColumn();
    placeRatingColumn.setSortable(true);
    sortHandler.setComparator(placeRatingColumn, Comparator.comparing(PlaceDTO::getRating));
    cellTable.addColumn(placeRatingColumn, "Rating");

    final Column<PlaceDTO, String> placeRatingsCountColumn = getPlaceRatingsCountColumn();
    placeRatingsCountColumn.setSortable(true);
    sortHandler.setComparator(placeRatingsCountColumn, Comparator.comparing(PlaceDTO::getRatingsCount));
    cellTable.addColumn(placeRatingsCountColumn, "Ratings count");

    selectionModel = new SingleSelectionModel<>();
    cellTable.setSelectionModel(selectionModel);

    final Label label = new Label("No data");
    cellTable.setEmptyTableWidget(label);

    pager.setRangeLimited(true);
    pager.setDisplay(cellTable);

    dataProvider.addDataDisplay(cellTable);
  }

  public void updateData(final List<PlaceDTO> places) {
    sortHandler.setList(places);
    dataProvider.setList(places);
    dataProvider.refresh();
  }

  public List<PlaceDTO> getData() {
    return dataProvider.getList();
  }

  public void addSelectionChangeHandler(SelectionChangeEvent.Handler handler) {
    selectionModel.addSelectionChangeHandler(handler);
  }

  public PlaceDTO getSelected() {
    return selectionModel.getSelectedObject();
  }

  private Column<PlaceDTO, String> getPlaceNameColumn() {
    return new Column<PlaceDTO, String>(new TextCell()) {
      @Override
      public String getValue(PlaceDTO placeDTO) {
        return placeDTO.getName();
      }
    };
  }

  private Column<PlaceDTO, String> getPlaceAddressColumn() {
    return new Column<PlaceDTO, String>(new TextCell()) {
      @Override
      public String getValue(PlaceDTO placeDTO) {
        return placeDTO.getAddress();
      }
    };
  }

  private Column<PlaceDTO, String> getPlaceRatingColumn() {
    return new Column<PlaceDTO, String>(new TextCell()) {
      @Override
      public String getValue(PlaceDTO placeDTO) {
        return String.valueOf(placeDTO.getRating());
      }
    };
  }

  private Column<PlaceDTO, String> getPlaceRatingsCountColumn() {
    return new Column<PlaceDTO, String>(new TextCell()) {
      @Override
      public String getValue(PlaceDTO placeDTO) {
        return String.valueOf(placeDTO.getRatingsCount());
      }
    };
  }
}
