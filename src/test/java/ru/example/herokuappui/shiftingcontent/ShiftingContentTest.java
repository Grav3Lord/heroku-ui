package ru.example.herokuappui.shiftingcontent;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.shiftingcontent.page.ShiftingContentPage;
import ru.example.herokuappui.shiftingcontent.page.image.ImagePage;
import ru.example.herokuappui.shiftingcontent.page.list.ListPage;
import ru.example.herokuappui.shiftingcontent.page.menuelement.MenuElementPage;
import utils.BaseDriver;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftingContentTest {

    private static ShiftingContentPage shiftingContentPage;
    private static MenuElementPage menuElementPage;
    private static ImagePage imagePage;
    private static ListPage listPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        shiftingContentPage = new ShiftingContentPage();
        menuElementPage = new MenuElementPage();
        imagePage = new ImagePage();
        listPage = new ListPage();
    }

    @Test
    public void testShiftingContentMainPage() {
        BaseDriver.openPage("/shifting_content");

        // Проверяем заголовок
        String headingText = shiftingContentPage.getPageHeadingText();
        assertEquals("Shifting Content", headingText, "Page heading should be 'Shifting Content'");

        // Проверяем количество ссылок на примеры
        int linkCount = shiftingContentPage.getExampleLinksCount();
        assertEquals(3, linkCount, "There should be 3 example links");
    }

    @Test
    public void testMenuElementShifting() {
        BaseDriver.openPage("/shifting_content");
        shiftingContentPage.openMenuElementPage();
        int menuItemsCount = menuElementPage.getMenuItemsCount();
        assertTrue(menuItemsCount > 0, "Menu should have at least one item");
        String initValue = menuElementPage.getFirstMenuItemShift();
        menuElementPage.refreshPage();
        String actualValue = menuElementPage.getFirstMenuItemShift();
        assertNotEquals(initValue, actualValue,
                "Shift value should change after refresh. Initial: " + initValue + ", New: " + actualValue);
    }

    @Test
    public void testImageShifting() {
        BaseDriver.openPage("/shifting_content");
        shiftingContentPage.openImagePage();
        String initValue = imagePage.getImageShift();
        Selenide.refresh();
        String actualValue = imagePage.getImageShift();
        assertNotEquals(initValue, actualValue,
                "Shift value should change after refresh. Initial: " + initValue + ", New: " + actualValue);

    }

    @Test
    public void testListShifting() {
        BaseDriver.openPage("/shifting_content");
        shiftingContentPage.openListPage();
        int listItemsCount = listPage.getListItemsCount();
        assertTrue(listItemsCount > 0, "List should have at least one item");
        String[] initContent = listPage.getListContent();
        BaseDriver.openPage("/shifting_content");
        shiftingContentPage.openListPage();
        String[] actualContent = listPage.getListContent();
        assertFalse(Arrays.equals(initContent, actualContent),
                "List content should change after refresh. Initial: " + Arrays.toString(initContent) +
                        ", New: " + Arrays.toString(actualContent));
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
