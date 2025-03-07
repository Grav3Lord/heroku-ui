package ru.example.herokuappui.geolocation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.geolocation.page.GeolocationPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.*;

public class GeolocationTest {
    private static GeolocationPage geolocationPage;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        geolocationPage = new GeolocationPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testGeolocationWithMockLocation() {
        BaseDriver.openPage("/geolocation");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        double mockLatitude = 51.5074;
        double mockLongitude = -0.1278;
        geolocationPage.setMockGeolocation(mockLatitude, mockLongitude);

        geolocationPage.clickWhereAmIButton();
        String latitude = geolocationPage.getLatitudeValue();
        String longitude = geolocationPage.getLongitudeValue();

        assertNotNull(latitude, "Latitude should be retrieved");
        assertNotNull(longitude, "Longitude should be retrieved");
        assertEquals(String.valueOf(mockLatitude), latitude, "Latitude should match mock value");
        assertEquals(String.valueOf(mockLongitude), longitude, "Longitude should match mock value");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
