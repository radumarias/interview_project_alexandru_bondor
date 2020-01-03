package com.places.integration.service;

import com.google.inject.Inject;
import com.places.integration.config.GuiceTestRunner;
import com.places.integration.config.WithModules;
import com.places.server.config.AppExternalClientsModule;
import com.places.server.config.AppModule;
import com.places.client.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GuiceTestRunner.class)
@WithModules({AppModule.class, AppExternalClientsModule.class})
public class PlaceServiceTest {

  @Inject
  private PlaceService placeService;

  @Test
  public void test() {
//    List<CityDTO> cities = placeService.getCities("clu");
//    Assert.assertNotNull(cities);
//    Assert.assertEquals(5, cities.size());
  }
}
