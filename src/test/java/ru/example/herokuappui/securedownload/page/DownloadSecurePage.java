package ru.example.herokuappui.securedownload.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.herokuappui.securedownload.util.Credentials;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DownloadSecurePage {

    private static final Logger logger = LoggerFactory.getLogger(DownloadSecurePage.class);
    private final Credentials credentials = new Credentials();

    @As("Page heading")
    private SelenideElement pageHeading = $("h3");

    @As("Download links")
    private ElementsCollection downloadLinks = $$("a[href*='/download']");

    @Step("Open page with authentication")
    public void openWithAuth() {
        logger.info("Opening download secure page with authentication");
        String authUrl = String.format("https://%s:%s@the-internet.herokuapp.com/download_secure",
                credentials.getUsername(), credentials.getPassword());
        com.codeborne.selenide.Selenide.open(authUrl);
        pageHeading.shouldBe(visible);
    }

    @Step("Click to download file at index {index}")
    public File downloadFile(int index) {
        logger.info("Clicking to download file at index {}", index);
        SelenideElement link = downloadLinks.get(index);
        link.shouldBe(visible);

        File downloadedFile = link.download();
        logger.info("File downloaded successfully: {}", downloadedFile.getAbsolutePath());
        return downloadedFile;
    }

    @Step("Get number of download links")
    public int getDownloadLinkCount() {
        logger.info("Getting number of download links");
        return downloadLinks.size();
    }
}
