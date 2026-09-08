package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends BaseTest {

    @Test
    public void validLoginRedirectsToInventoryPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(TestUsers.STANDARD_USERNAME, TestUsers.PASSWORD);

        assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Expected to land on the inventory page after a valid login");
    }

    @Test
    public void invalidPasswordShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(TestUsers.STANDARD_USERNAME, "wrong_password");

        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("do not match"),
                "Expected an error about mismatched username/password, got: " + error);
    }

    @Test
    public void emptyCredentialsShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("", "");

        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("Username is required"),
                "Expected an error asking for a username, got: " + error);
    }

    @Test
    public void lockedOutUserShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(TestUsers.LOCKED_OUT_USERNAME, TestUsers.PASSWORD);

        String error = loginPage.getErrorMessage();
        assertTrue(error.contains("locked out"),
                "Expected an error about the account being locked out, got: " + error);
    }
}
