package ru.example.herokuappui.frame.page.iframe;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class IFramePage {
    private static final Logger logger = LoggerFactory.getLogger(IFramePage.class);
    @As("Iframe")
    private SelenideElement iframe = $("#mce_0_ifr");
    @As("Iframe close button")
    private SelenideElement iframeCloseButton = $("div.tox-icon");
    @As("Iframe textarea")
    private SelenideElement iframeTextArea = $("body[id='tinymce'] > p");

    @Step("Get text from iFrame")
    public String getIframeText() {
        try {
            iframe.click();
            iframeCloseButton.click();
            switchTo().frame(iframe);
            String text = iframeTextArea.getText();
            switchTo().defaultContent();
            logger.info("Retrieved text from iFrame: {}", text);
            return text;
        } catch (Exception e) {
            logger.warn("Failed to get text from iFrame: {}", e.getMessage());
            return null;
        }
    }
}
