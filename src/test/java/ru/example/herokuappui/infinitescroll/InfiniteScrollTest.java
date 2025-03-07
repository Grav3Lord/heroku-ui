package ru.example.herokuappui.infinitescroll;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.infinitescroll.page.InfiniteScrollPage;
import utils.BaseDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteScrollTest {
    private static InfiniteScrollPage infiniteScrollPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        infiniteScrollPage = new InfiniteScrollPage();
    }

    @Test
    public void testInfiniteScroll() {
        BaseDriver.openPage("/infinite_scroll");
        int initialCount = infiniteScrollPage.getParagraphCount();
        assertTrue(initialCount > 0, "Initial paragraph count should be greater than 0");

        for (int i = 0; i < 3; i++) {
            Selenide.sleep(500);
            infiniteScrollPage.scrollToBottom();
        }

        int finalCount = infiniteScrollPage.getParagraphCount();
        assertTrue(infiniteScrollPage.verifyParagraphCount(initialCount + 3),
                "At least 3 more paragraphs should be loaded, initial: " + initialCount + ", final: " + finalCount);
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
