package com.places.integration.service;

import com.google.inject.Inject;
import com.places.integration.config.AppTestModule;
import com.places.integration.config.GuiceTestRunner;
import com.places.integration.config.WithModules;
import com.places.server.entity.Place;
import com.places.server.service.PlaceService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GuiceTestRunner.class)
@WithModules({AppTestModule.class})
public class PlaceServiceTest {

  @Inject
  private PlaceService placeService;

  private final String PLACE_NAME = "placeName";
  private final String PLACE_ADDRESS = "address";
  private final String PLACE_PHONE_NUMBER = "phoneNumber";
  private final Double PLACE_RATING = 5.0;
  private final Integer PLACE_RATINGS_COUNT = 10;
  private final String PLACE_GOOGLE_PLACE_ID = "googlePlaceId";
  private final Double PLACE_LAT = 10.0;
  private final Double PLACE_LNG = 20.0;
  private final String PLACE_WEBSITE = "website";
  private final String PLACE_PHOTO_REFERENCE = "photoReference";

  @Test
  public void testSavePlace() {
    final Place place = new Place();
    place.setName(PLACE_NAME);
    place.setAddress(PLACE_ADDRESS);
    place.setPhoneNumber(PLACE_PHONE_NUMBER);
    place.setRating(PLACE_RATING);
    place.setRatingsCount(PLACE_RATINGS_COUNT);
    place.setGooglePlaceId(PLACE_GOOGLE_PLACE_ID);
    place.setLat(PLACE_LAT);
    place.setLng(PLACE_LNG);
    place.setWebsite(PLACE_WEBSITE);
    List<String> photoReferences = new ArrayList<>();
    photoReferences.add(PLACE_PHOTO_REFERENCE);
    place.setPhotoReferences(photoReferences);
    place.setUpdated(false);

    final Place savedPlace = placeService.save(place);

    Assert.assertEquals(PLACE_NAME, savedPlace.getName());
    Assert.assertEquals(PLACE_ADDRESS, savedPlace.getAddress());
    Assert.assertEquals(PLACE_PHONE_NUMBER, savedPlace.getPhoneNumber());
    Assert.assertEquals(PLACE_RATING, savedPlace.getRating());
    Assert.assertEquals(PLACE_RATINGS_COUNT, savedPlace.getRatingsCount());
    Assert.assertEquals(PLACE_GOOGLE_PLACE_ID, savedPlace.getGooglePlaceId());
    Assert.assertEquals(PLACE_LAT, savedPlace.getLat());
    Assert.assertEquals(PLACE_LNG, savedPlace.getLng());
    Assert.assertNotNull(savedPlace.getPhotoReferences());
    Assert.assertEquals(1, savedPlace.getPhotoReferences().size());
    Assert.assertEquals(PLACE_PHOTO_REFERENCE, savedPlace.getPhotoReferences().get(0));
    Assert.assertFalse(savedPlace.isUpdated());
  }

  @Test
  public void testDeleteUnexisting() {
    final Place entity = new Place();
    entity.setId(123456L);
    Assert.assertFalse(placeService.delete(entity));
  }
}
