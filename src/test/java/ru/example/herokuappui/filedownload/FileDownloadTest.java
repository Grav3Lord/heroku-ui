package ru.example.herokuappui.filedownload;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.common.CommonElementsPage;
import ru.example.herokuappui.filedownload.page.FileDownloadPage;
import utils.BaseDriver;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileDownloadTest {

    private static FileDownloadPage fdp;
    private static CommonElementsPage cep;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        // Настройка директории загрузки
        Configuration.downloadsFolder = System.getProperty("user.home") + "/Downloads/";
        fdp = new FileDownloadPage();
        cep = new CommonElementsPage();
    }

    @Test
    public void testFirstFileDownload() {
        BaseDriver.openPage("/download");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        File downloadedFile = fdp.downloadFile(0);
        assertTrue(fdp.isFileDownloaded(downloadedFile.getName()), "First file should be downloaded: " + downloadedFile.getName());

        // Очистка после теста
        fdp.deleteDownloadedFile(downloadedFile.getName());
    }

    @Test
    public void testSecondFileDownloadAndContent() {
        BaseDriver.openPage("/download");
        assertTrue(cep.isPageHeaderVisible(), "Page header should be visible");

        File downloadedFile = fdp.downloadFile(3);
        assertTrue(fdp.isFileDownloaded(downloadedFile.getName()), "Second file should be downloaded: " + downloadedFile.getName());
        String fileContent = "{\\rtf1\\ansi\\ansicpg1252\\cocoartf1348\\cocoasubrtf170\n" +
                "{\\fonttbl\\f0\\fswiss\\fcharset0 Helvetica;}\n" +
                "{\\colortbl;\\red255\\green255\\blue255;}\n" +
                "\\margl1440\\margr1440\\vieww10800\\viewh8400\\viewkind0\n" +
                "\\pard\\tx720\\tx1440\\tx2160\\tx2880\\tx3600\\tx4320\\tx5040\\tx5760\\tx6480\\tx7200\\tx7920\\tx8640\\pardirnatural\n" +
                "\n" +
                "\\f0\\fs24 \\cf0 asdf}";
        assertTrue(fdp.verifyFileContent(downloadedFile, fileContent), "Second file content should match");

        // Очистка после теста
        fdp.deleteDownloadedFile(downloadedFile.getName());
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
