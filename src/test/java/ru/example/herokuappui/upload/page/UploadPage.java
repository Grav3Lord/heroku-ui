package ru.example.herokuappui.upload.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.codeborne.selenide.Selenide.$;

public class UploadPage {
    private static final Logger logger = LoggerFactory.getLogger(UploadPage.class);
    @As("Input for upload")
    private SelenideElement fileInput = $("#file-upload");
    @As("Upload button")
    private SelenideElement uploadButton = $("#file-submit");
    @As("List of uploaded files")
    private SelenideElement uploadedFiles = $("div[id='uploaded-files']");
    @As("Upload title")
    private SelenideElement uploadTitle = $("h3");

    @Step("Generate temporary file for upload")
    public File generateTempFile(String fileName, String content) {
        try {
            // Создаём временный файл с заданным именем и содержимым
            Path tempFilePath = Files.createTempFile(fileName, ".txt");
            Files.write(tempFilePath, content.getBytes());
            File tempFile = tempFilePath.toFile();
            logger.info("Generated temporary file: {}", tempFile.getAbsolutePath());
            return tempFile;
        } catch (Exception e) {
            logger.error("Failed to generate temporary file: {}", e.getMessage());
            throw new RuntimeException("Could not generate temporary file", e);
        }
    }

    @Step("Upload file {file}")
    public void uploadFile(File file) {
        logger.info("Uploading file: {}", file.getName());
        fileInput.uploadFile(file);
        uploadButton.click();
        logger.info("File upload initiated: {}", file.getName());
    }

    @Step("Check if upload was successful")
    public boolean isUploadSuccessful() {
        try {
            return uploadedFiles.isDisplayed() && uploadTitle.getText().contains("File Uploaded!");
        } catch (Exception e) {
            logger.warn("Upload not successful: {}", e.getMessage());
            return false;
        }
    }

    @Step("Get uploaded file name")
    public String getUploadedFileName() {
        try {
            return uploadedFiles.getText().replace("File Uploaded!", "").trim();
        } catch (Exception e) {
            logger.warn("Failed to get uploaded file name: {}", e.getMessage());
            return null;
        }
    }

    @Step("Delete temporary file {file}")
    public void deleteTempFile(File file) {
        if (file != null && file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                logger.info("Deleted temporary file: {}", file.getAbsolutePath());
            } else {
                logger.warn("Failed to delete temporary file: {}", file.getAbsolutePath());
            }
        }
    }
}
