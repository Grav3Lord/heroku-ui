package ru.example.herokuappui.checkboxes.page;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class CheckboxesPage {

    @As("List of Checkboxes")
    private final ElementsCollection checkboxes = $$("#checkboxes input[type='checkbox']");

    @Step("Click on checkbox by index {0}")
    public void setCheckbox(int index, boolean check) {
        checkboxes.get(index).setSelected(check);
    }

    @Step("Verify, that checkbox is checkd")
    public boolean isCheckboxChecked(int index) {
        return checkboxes.get(index).isSelected();
    }

    @Step("Verify checkbox count")
    public int getCheckboxCount() {
        return checkboxes.size();
    }

}
