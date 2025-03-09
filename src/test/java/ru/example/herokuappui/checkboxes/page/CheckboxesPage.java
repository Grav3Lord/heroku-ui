package ru.example.herokuappui.checkboxes.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$;

public class CheckboxesPage {

    private static final Logger logger = LoggerFactory.getLogger(CheckboxesPage.class);

    @As("List of Checkboxes")
    private final ElementsCollection checkboxes = $$("#checkboxes input[type='checkbox']");

    @Step("Setting {1} in checkbox by index {0}")
    public void setCheckbox(int index, boolean check) {
        logger.info("Setting {} in checkbox by index {}", index, check);
        checkboxes.get(index).setSelected(check);
    }

    @Step("Verify, that checkbox is checkd")
    public boolean isCheckboxChecked(int index) {
        logger.info("Verifying checkbox {} is checked", index);
        return checkboxes.get(index).isSelected();
    }

    @Step("Verify checkbox count")
    public int getCheckboxCount() {
        logger.info("Verifying checkbox count");
        return checkboxes.size();
    }

}
