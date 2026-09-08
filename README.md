# SauceDemo Selenium Test Suite

Automated UI test suite for [SauceDemo](https://www.saucedemo.com/), a public
demo e-commerce site built specifically for practicing test automation.

Built as a learning project to practice Selenium WebDriver, Java, JUnit 5,
and the Page Object Model (POM) pattern.

## What it tests

- **Login** (`LoginTest`): valid login, wrong password, empty fields, locked-out user
- **Cart** (`CartTest`): adding one product, adding two products and verifying the cart contents
- **Sorting** (`SortingTest`): sorting products by name (A-Z, Z-A) and price (low-high, high-low)

## Tech stack

- Java 17
- Selenium WebDriver 4
- JUnit 5
- WebDriverManager (automatic ChromeDriver management)
- Maven

## Project structure

```
src/main/java/pages/     -> Page Object classes (LoginPage, InventoryPage, CartPage)
src/test/java/tests/     -> Test classes (BaseTest, LoginTest, CartTest)
```

Each page of the site has its own Page Object class that hides *where*
elements are (locators) behind methods that describe *what* you can do on
that page (`login(...)`, `addProductToCart(...)`, etc). Test classes call
those methods instead of finding elements directly - this is the **Page
Object Model** pattern used in most real-world Selenium projects.

## Running the tests

Requirements: JDK 17+, Maven, and Google Chrome installed.

```bash
mvn test
```

WebDriverManager will automatically download the matching ChromeDriver on
first run. A Chrome window will open and run through each test. To run
without a visible browser window, uncomment the `--headless=new` line in
`BaseTest.java`.

## Why this project exists

I'm a software tester learning Java and Selenium in more depth. This project
is my practice ground for writing real automated tests with a proper
structure (Page Object Model), rather than one big script. It's a work in
progress - next steps I'm planning:

- [ ] Add checkout flow tests
- [ ] Parameterize login tests with `@ParameterizedTest` instead of separate methods
- [ ] Add a GitHub Actions workflow to run tests automatically on push
- [ ] Add Allure or JUnit HTML reporting
