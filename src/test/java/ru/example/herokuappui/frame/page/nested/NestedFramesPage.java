package ru.example.herokuappui.frame.page.nested;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.herokuappui.frame.page.FramesPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class NestedFramesPage {
    private static final Logger logger = LoggerFactory.getLogger(NestedFramesPage.class);
    @As("Top frame")
    private SelenideElement topFrame = $("frame[name='frame-top']");
    @As("Left frame")
    private SelenideElement leftFrame = $("frame[name='frame-left']");
    @As("Middle frame")
    private SelenideElement middleFrame = $("frame[name='frame-middle']");
    @As("Right frame")
    private SelenideElement rightFrame = $("frame[name='frame-right']");
    @As("Bottom frame")
    private SelenideElement bottomFrame = $("frame[name='frame-bottom']");

    @Step("Switch to Nested Frames and get text from {0}")
    private String getFrameText(String frameDescription, SelenideElement... frames) {
        try {
            for (SelenideElement frame : frames) {
                switchTo().frame(frame);
            }
            SelenideElement textElement = $("body");
            String text = textElement.getText();
            logger.info("Retrieved text from {}: {}", frameDescription, text);
            return text;
        } catch (Exception e) {
            logger.warn("Failed to get text from {}: {}", frameDescription, e.getMessage());
            return null;
        } finally {
            switchTo().defaultContent();
        }
    }

    @Step("Switch to Nested Frames and get text from Left frame")
    public String getLeftFrameText() {
        return getFrameText("Left frame", topFrame, leftFrame);
    }

    @Step("Switch to Nested Frames and get text from Middle frame")
    public String getMiddleFrameText() {
        return getFrameText("Middle frame", topFrame, middleFrame);
    }

    @Step("Switch to Nested Frames and get text from Right frame")
    public String getRightFrameText() {
        return getFrameText("Right frame", topFrame, rightFrame);
    }

    @Step("Switch to Nested Frames and get text from Bottom frame")
    public String getBottomFrameText() {
        return getFrameText("Bottom frame", bottomFrame);
    }
}
