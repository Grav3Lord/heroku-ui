package ru.example.herokuappui.hovers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.hovers.page.HoverPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HoversTest {
    private static HoverPage hoverPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        hoverPage = new HoverPage();
    }

    @Test
    public void testHoverOverUser() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = "https://the-internet.herokuapp.com";
        }

        BaseDriver.openPage("/hovers");
        assertEquals(3, hoverPage.getProfilesCount(), "There should be 3 user images");

        hoverPage.hoverOverUser(0);
        assertTrue(hoverPage.verifyUserName(0, "user1"), "User name should be user1");
        assertEquals(baseUrl + "/users/1", hoverPage.getViewProfileLink(0), "Profile link should be /users/1");

        hoverPage.hoverOverUser(2);
        assertTrue(hoverPage.verifyUserName(2, "user3"), "User name should be user3");
        assertEquals(baseUrl + "/users/3", hoverPage.getViewProfileLink(2), "Profile link should be /users/3");

        hoverPage.hoverOverUser(1);
        assertTrue(hoverPage.verifyUserName(1, "user2"), "User name should be user2");
        assertEquals( baseUrl + "/users/2", hoverPage.getViewProfileLink(1), "Profile link should be /users/2");

    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
