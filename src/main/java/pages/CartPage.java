package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPage {

    private final WebDriver driver;

    private final By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    /** Returns how many distinct product rows are listed in the cart. */
    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }
}
