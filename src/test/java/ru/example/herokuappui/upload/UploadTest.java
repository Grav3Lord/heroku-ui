package ru.example.herokuappui.upload;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.upload.page.UploadPage;
import utils.BaseDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadTest {
    private static UploadPage uploadPage;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        uploadPage = new UploadPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testFileUpload() {
        BaseDriver.openPage("/upload");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        // Генерируем временный файл
        File tempFile = uploadPage.generateTempFile("testfile", "Test content for upload");

        // Загружаем файл
        uploadPage.uploadFile(tempFile);
        assertTrue(uploadPage.isUploadSuccessful(), "Upload should be successful");
        String uploadedFileName = uploadPage.getUploadedFileName();
        assertTrue(uploadedFileName != null && uploadedFileName.contains("testfile"), "Uploaded file name should match");

        // Удаляем временный файл
        uploadPage.deleteTempFile(tempFile);
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
