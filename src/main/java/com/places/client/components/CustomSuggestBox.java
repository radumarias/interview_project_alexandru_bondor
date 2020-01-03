package com.places.client.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.impl.Constants;

public class CustomSuggestBox extends Composite {

//  /**
//   * The constants used in this Content Widget.
//   */
//  public static interface CwConstants extends Constants {
//
//    String cwSuggestBoxDescription();
//
//    String cwSuggestBoxLabel();
//
//    String cwSuggestBoxName();
//
//    String[] cwSuggestBoxWords();
//  }
//
//  /**
//   * An instance of the constants.
//   */
//  private final CwConstants constants;
//
//  /**
//   * Initialize this example.
//   */
//  @Override
//  public Widget onInitialize() {
//    // Define the oracle that finds suggestions
//    MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
//    String[] words = constants.cwSuggestBoxWords();
//    for (String word : words) {
//      oracle.add(word);
//    }
//
//    // Create the suggest box
//    final SuggestBox suggestBox = new SuggestBox(oracle);
//    suggestBox.ensureDebugId("cwSuggestBox");
//    VerticalPanel suggestPanel = new VerticalPanel();
//    suggestPanel.add(new HTML(constants.cwSuggestBoxLabel()));
//    suggestPanel.add(suggestBox);
//
//    // Return the panel
//    return suggestPanel;
//  }

}
