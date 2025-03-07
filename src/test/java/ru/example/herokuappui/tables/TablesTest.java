package ru.example.herokuappui.tables;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.example.herokuappui.tables.page.TablesPage;
import utils.BaseDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TablesTest {

    private static TablesPage tablesPage;

    @BeforeAll
    public static void init() {
        BaseDriver.setup();
        tablesPage = new TablesPage();
    }

    @Test
    public void testTable1HasData() {
        BaseDriver.openPage("/tables");
        int rowCount = tablesPage.getTable1RowCount();
        assertTrue(rowCount > 0, "Table 1 should have at least one row");
    }

    @Test
    public void testTable1LastNameSort() {
        BaseDriver.openPage("/tables");
        List<String> initialLastNames = tablesPage.getLastNameColumnValues();
        tablesPage.sortByLastName();
        List<String> sortedLastNames = tablesPage.getLastNameColumnValues();
        assertTrue(isSortedAscending(sortedLastNames),
                "Last Name column should be sorted in ascending order after clicking the header. Actual: " + sortedLastNames);
        assertFalse(initialLastNames.equals(sortedLastNames),
                "Last Name column values should change after sorting. Initial: " + initialLastNames + ", Sorted: " + sortedLastNames);
    }

    private boolean isSortedAscending(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    @AfterAll
    public static void cleanup() {
        BaseDriver.tearDown();
    }
}
