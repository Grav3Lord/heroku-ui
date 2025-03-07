package ru.example.herokuappui.securedownload;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.securedownload.page.DownloadSecurePage;
import utils.BaseDriver;

import java.io.File;

import static com.codeborne.selenide.Configuration.downloadsFolder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadSecureTest {
    private static DownloadSecurePage downloadPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        downloadPage = new DownloadSecurePage();
    }

    @Test
    public void testDownloadSecure() {
        // Открываем страницу с аутентификацией
        downloadPage.openWithAuth();

        // Проверяем наличие ссылок для скачивания
        int linkCount = downloadPage.getDownloadLinkCount();
        assertTrue(linkCount > 0, "At least one download link should be present");

        // Скачиваем первый файл
        File downloadedFile = downloadPage.downloadFile(0);
        assertNotNull(downloadedFile, "Downloaded file should not be null");
        assertTrue(downloadedFile.exists(), "Downloaded file should exist: " + downloadedFile.getAbsolutePath());
    }

    @AfterAll
    public static void cleanup() {
        File downloadDir = new File(downloadsFolder);
        for (File file : downloadDir.listFiles()) {
            file.deleteOnExit();
        }
        BaseDriver.tearDown();
    }
}
