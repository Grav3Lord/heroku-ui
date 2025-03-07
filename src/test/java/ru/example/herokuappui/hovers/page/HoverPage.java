package ru.example.herokuappui.hovers.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$;

public class HoverPage {
    private static final Logger logger = LoggerFactory.getLogger(HoverPage.class);

    @As("List of profiles")
    private ElementsCollection figures = $$(".figure");

    @Step("Hover over user image at index {index}")
    public void hoverOverUser(int index) {
        logger.info("Hovering over user image at index {}", index);
        figures.get(index).hover();
    }

    @Step("Get profiles count")
    public int getProfilesCount() {
        return figures.size();
    }

    @Step("Get displayed user name")
    public String getUserName(int index) {
        logger.info("Getting displayed user name");
        return figures.get(index).$("h5").getText().replace("name: ", "").trim();
    }

    @Step("Get view profile link")
    public String getViewProfileLink(int index) {
        logger.info("Getting view profile link");
        return figures.get(index).$("a").getAttribute("href");
    }

    @Step("Verify user name is {expectedName}")
    public boolean verifyUserName(int index, String expectedName) {
        String actualName = getUserName(index);
        logger.info("Verify user name is {}", actualName);
        boolean isMatch = actualName.contains(expectedName);
        logger.info("Verified user name: expected={}, actual={}, match={}", expectedName, actualName, isMatch);
        return isMatch;
    }
}
