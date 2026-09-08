package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SortingTest extends BaseTest {

    @BeforeEach
    public void loginBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(TestUsers.STANDARD_USERNAME, TestUsers.PASSWORD);
    }

    @Test
    public void sortingByNameAZOrdersProductsAlphabetically() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("az");

        List<String> displayedNames = inventoryPage.getProductNames();


        List<String> expectedOrder = new ArrayList<>(displayedNames);
        Collections.sort(expectedOrder);

        assertEquals(expectedOrder, displayedNames,
                "Products should be listed alphabetically (A to Z) after selecting that sort option");
    }

    @Test
    public void sortingByNameZAOrdersProductsReverseAlphabetically() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("za");

        List<String> displayedNames = inventoryPage.getProductNames();

        List<String> expectedOrder = new ArrayList<>(displayedNames);
        expectedOrder.sort(Collections.reverseOrder());

        assertEquals(expectedOrder, displayedNames,
                "Products should be listed reverse-alphabetically (Z to A) after selecting that sort option");
    }

    @Test
    public void sortingByPriceLowToHighOrdersProductsAscending() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("lohi");

        List<Double> displayedPrices = inventoryPage.getProductPrices();

        List<Double> expectedOrder = new ArrayList<>(displayedPrices);
        Collections.sort(expectedOrder);

        assertEquals(expectedOrder, displayedPrices,
                "Prices should be listed from lowest to highest after selecting that sort option");
    }

    @Test
    public void sortingByPriceHighToLowOrdersProductsDescending() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortBy("hilo");

        List<Double> displayedPrices = inventoryPage.getProductPrices();

        List<Double> expectedOrder = new ArrayList<>(displayedPrices);
        expectedOrder.sort(Collections.reverseOrder());

        assertEquals(expectedOrder, displayedPrices,
                "Prices should be listed from highest to lowest after selecting that sort option");
    }
}
