package ru.example.herokuappui.filedownload.page;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.$$;

public class FileDownloadPage {
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadPage.class);
    private ElementsCollection fileLinks = $$("#content a");

    // Путь к директории загрузки
    private final String downloadDir = System.getProperty("user.home") + "/Downloads/";

    @Step("Download the first file")
    public File downloadFile(int index) {
        String fileName = fileLinks.get(index).getText();
        logger.info("Attempting to download first file: {}", fileName);
        File downloadedFile = fileLinks.get(index).download();
        logger.info("First file downloaded: {}", downloadedFile.getName());
        return downloadedFile;
    }

    @Step("Check if file {fileName} was downloaded")
    public boolean isFileDownloaded(String fileName) {
        try {
            Path filePath = Paths.get(downloadDir + fileName);
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 10000) { // 10 секунд
                if (Files.exists(filePath)) {
                    logger.info("File found in download directory: {}", filePath);
                    return true;
                }
                Thread.sleep(500); // Проверяем каждые 500 мс
            }
            logger.warn("File not found in download directory after 10 seconds: {}", filePath);
            return false;
        } catch (InterruptedException e) {
            logger.error("Error while checking file download: {}", e.getMessage());
            return false;
        }
    }

    @Step("Verify content of file {fileName}")
    public boolean verifyFileContent(File downloadedFile, String expectedContent) {
        try {
            String content = new String(Files.readAllBytes(downloadedFile.toPath()));
            logger.info("File content: {}", content);
            return content.contains(expectedContent);
        } catch (IOException e) {
            logger.error("Error reading file content: {}", e.getMessage());
            return false;
        }
    }

    @Step("Delete downloaded file {fileName}")
    public void deleteDownloadedFile(String fileName) {
        try {
            Path filePath = Paths.get(downloadDir + fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                logger.info("Deleted downloaded file: {}", filePath);
            }
        } catch (Exception e) {
            logger.error("Error while deleting file: {}", e.getMessage());
        }
    }
}
