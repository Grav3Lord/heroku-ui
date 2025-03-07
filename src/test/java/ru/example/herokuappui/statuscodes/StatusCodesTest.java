package ru.example.herokuappui.statuscodes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.statuscodes.page.StatusCodesPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatusCodesTest {

    private static StatusCodesPage statusCodesPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        statusCodesPage = new StatusCodesPage();
    }

    @Test
    public void testStatusCodesPageLoads() {
        BaseDriver.openPage("/status_codes");
        int linkCount = statusCodesPage.getStatusLinksCount();
        assertTrue(linkCount > 0, "Status Codes page should have at least one link");
    }

    @Test
    public void testStatusLink() {
        BaseDriver.openPage("/status_codes");
        statusCodesPage.clickStatusLinkAndVerify("200");

        BaseDriver.openPage("/status_codes");
        statusCodesPage.clickStatusLinkAndVerify("301");

        BaseDriver.openPage("/status_codes");
        statusCodesPage.clickStatusLinkAndVerify("404");

        BaseDriver.openPage("/status_codes");
        statusCodesPage.clickStatusLinkAndVerify("500");
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
