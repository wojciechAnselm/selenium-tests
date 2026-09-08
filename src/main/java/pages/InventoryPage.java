package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;


public class InventoryPage {

    private final WebDriver driver;

    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }


    public void addProductToCart(String productSlug) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        By locator = By.id("add-to-cart-" + productSlug);
        WebElement button = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
        button.click();
    }


    public int getCartItemCount() {
        try {
            return Integer.parseInt(driver.findElement(cartBadge).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }


    public void goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement cart = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(cartLink));
        cart.click();
    }



    public void sortBy(String sortOptionValue) {
        Select sortSelect = new Select(driver.findElement(sortDropdown));
        sortSelect.selectByValue(sortOptionValue);
    }


    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }


    public List<Double> getProductPrices() {
        return driver.findElements(productPrices)
                .stream()
                .map(element -> Double.parseDouble(element.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}

