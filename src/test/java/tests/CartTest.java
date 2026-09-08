package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CartTest extends BaseTest {

    @BeforeEach
    public void loginBeforeEachTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(TestUsers.STANDARD_USERNAME, TestUsers.PASSWORD);
    }

    @Test
    public void addingOneProductUpdatesCartBadge() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("sauce-labs-backpack");

        assertEquals(1, inventoryPage.getCartItemCount(),
                "Cart badge should show 1 item after adding a single product");
    }

    @Test
    public void addingTwoProductsShowsBothInCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart("sauce-labs-backpack");
        inventoryPage.addProductToCart("sauce-labs-bike-light");
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        assertEquals(2, cartPage.getCartItemCount(),
                "Cart page should list both products that were added");
    }
}
