package ru.example.herokuappui.draganddrop.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class DragAndDropPage {
    private SelenideElement columnA = $("#column-a");
    private SelenideElement columnB = $("#column-b");


    public void dragAtoB() {
        actions().dragAndDrop(columnA, columnB).perform();
    }

    public String getColumnAText() {
        return columnA.shouldBe(visible).getText();
    }

    public String getColumnBText() {
        return columnB.shouldBe(visible).getText();
    }

    public boolean areColumnsVisible() {
        return columnA.isDisplayed() && columnB.isDisplayed();
    }
}
